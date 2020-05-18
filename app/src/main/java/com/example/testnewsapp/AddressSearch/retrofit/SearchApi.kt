package com.example.testnewsapp.AddressSearch.retrofit

import com.example.testnewsapp.AddressSearch.models.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("autocomplete")
    fun getSearchAddress(
        @Query("queryString") query: String?,
        @Query("city") city: String?
    ): Call<SearchResponse?>?
}