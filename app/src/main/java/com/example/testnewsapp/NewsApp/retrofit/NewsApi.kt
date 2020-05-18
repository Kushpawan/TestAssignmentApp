package com.example.testnewsapp.NewsApp.retrofit

import com.example.testnewsapp.NewsApp.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    fun getNewsList(
        @Query("country") newsCountry: String?,
        @Query("apiKey") apiKey: String?
    ): Call<NewsResponse?>?
}