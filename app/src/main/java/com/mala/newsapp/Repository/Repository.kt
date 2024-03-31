package com.mala.newsapp.Repository

import com.mala.newsapp.api.RetrofitInstance
import com.mala.newsapp.data.DataBase
import com.mala.newsapp.model.News
import retrofit2.Response

class Repository(
    val db: DataBase
){
    suspend fun getBreakingNews(country:String,pageNumber:Int):Response<News>{
       return RetrofitInstance.api.getBreakingNews(country,pageNumber)
    }
}