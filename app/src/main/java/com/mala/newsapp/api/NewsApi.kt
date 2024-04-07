package com.mala.newsapp.api

import com.mala.newsapp.model.News
import com.mala.newsapp.uitls.Constants
import com.mala.newsapp.uitls.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        country:String="us",
        @Query("page")
        pageNumber:Int=1,
        @Query("apikey")
        apikey:String= Constants.API_KEY):Response<News>

        @GET("/v2/everything")
    suspend fun getSearchNews(
        @Query("q")
        search:String,
        @Query("page")
        pageNumber:Int=1,
        @Query("apikey")
        apikey:String= Constants.API_KEY):Response<News>
}