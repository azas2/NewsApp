package com.mala.newsapp.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mala.newsapp.Repository.Repository
import com.mala.newsapp.model.Article
import com.mala.newsapp.model.News
import com.mala.newsapp.uitls.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
@HiltViewModel
class NewsViewModel @Inject constructor(
    val newsRepository: Repository
) :ViewModel() {
    var breakingNewsResponse:News?=null
    var searchNewsResponse:News?=null
val TAG="VIEWMODEL"
    private var _searchNews:MutableLiveData<Resource<News>> = MutableLiveData()
    val searchNews:LiveData<Resource<News>>
        get()=_searchNews

    private var pageNumberOfSearch=1
     private var  _breakingNews : MutableLiveData<Resource<News>> =MutableLiveData()
    val breakingnews:LiveData<Resource<News>>
        get()=_breakingNews
   private var breakingNewsPage=1

    init {
            getBreakingNews("us")
    }
    fun getBreakingNews(countryCode:String)=viewModelScope.launch {
        _breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        _breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<News>) : Resource<News> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingNewsPage++
                if(breakingNewsResponse==null){
                    breakingNewsResponse=resultResponse
                }
                else{
                    val oldBreakingNews=breakingNewsResponse?.articles
                    val newBreakingNews=resultResponse.articles
                    oldBreakingNews?.addAll(newBreakingNews)
                }
                return Resource.Success(breakingNewsResponse?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    fun SearchOfNews(txSearch:String)=viewModelScope.launch {
            _searchNews.postValue(Resource.Loading())
            val response=newsRepository.getSearchNews(txSearch,pageNumberOfSearch)
        _searchNews.postValue(handleSearchNewsResponse(response))
        }

    private fun handleSearchNewsResponse(response:Response<News>):Resource<News>{
        if(response.isSuccessful){
            response.body()?.let { newResponse ->
                pageNumberOfSearch++
                if(searchNewsResponse==null){
                    searchNewsResponse=newResponse
                }
                else{
                    val oldSearchNewsResponse= searchNewsResponse?.articles
                    val newSearchNewsResponse=newResponse.articles
                    oldSearchNewsResponse?.addAll(newSearchNewsResponse)
                }
               return Resource.Success(searchNewsResponse?:newResponse)
            }

        }
        return Resource.Error(response.message())
    }

    fun getAddData(article:Article){
        try {
            viewModelScope.launch {
                newsRepository.addData(article)
            }
        }catch (ex:Exception){
            Log.d(TAG,"${ex.message}")
        }
    }
    fun getSavedData()= newsRepository.getAllData()

    fun deleteArticle(article: Article)=viewModelScope.launch {
        newsRepository.deleteData(article)
    }
}