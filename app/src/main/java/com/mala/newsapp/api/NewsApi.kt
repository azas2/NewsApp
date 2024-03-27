package com.mala.newsapp.api

import com.mala.newsapp.uitls.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        country:String="us",
        @Query("page")
        pagenumber:Int=1,
        @Query("apikey")
        apikey:String= Constants.API_KEY)

        @GET("/v2/everything")
    suspend fun getSearchNews(
        @Query("country")
        search:String,
        @Query("page")
        page_number:Int=1,
        @Query("apikey")
        apikey:String= Constants.API_KEY
    )
}