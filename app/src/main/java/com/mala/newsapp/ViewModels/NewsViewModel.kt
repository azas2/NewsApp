package com.mala.newsapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mala.newsapp.Repository.Repository
import com.mala.newsapp.model.News
import com.mala.newsapp.uitls.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class NewsViewModel(
    val newsRepository: Repository
):ViewModel() {
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
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}