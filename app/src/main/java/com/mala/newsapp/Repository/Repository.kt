package com.mala.newsapp.Repository

import com.mala.newsapp.api.RetrofitInstance
import com.mala.newsapp.data.DataBase
import com.mala.newsapp.model.Article
import com.mala.newsapp.model.News
import retrofit2.Response

class Repository(
    val db: DataBase
){
    suspend fun getBreakingNews(country:String,pageNumber:Int):Response<News>{
       return RetrofitInstance.api.getBreakingNews(country,pageNumber)
    }
    suspend fun getSearchNews(search:String,pageNumber: Int):Response<News>{
        return RetrofitInstance.api.getSearchNews(search,pageNumber)
    }
    suspend fun addData(article:Article) {
        db.getAllArticleDao().addArticle(article)
    }
    fun getAllData()=db.getAllArticleDao().getAllArticle()
    suspend fun deleteData(article: Article)=db.getAllArticleDao().deleteArticle(article)
}