package com.francgar.livenewsfeed.api

import com.francgar.livenewsfeed.NewsResponse
import com.francgar.livenewsfeed.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "es",
        @Query("page") pageNumber: Int = 0,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 0,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

}