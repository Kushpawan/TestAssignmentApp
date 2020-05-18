package com.example.testnewsapp.AddressSearch.retrofit

import androidx.lifecycle.MutableLiveData
import com.example.testnewsapp.AddressSearch.models.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository {
    private val searchApi: SearchApi = com.example.testnewsapp.AddressSearch.retrofit.RetrofitService.createService(SearchApi::class.java)
    val searchData = MutableLiveData<SearchResponse?>()

    fun getAddressResult(query: String?, city: String?): MutableLiveData<SearchResponse?> {
        searchApi.getSearchAddress(query, city)!!.enqueue(object : Callback<SearchResponse?> {
            override fun onResponse(
                call: Call<SearchResponse?>,
                response: Response<SearchResponse?>
            ) {
                if (response.isSuccessful) {
                    searchData.value = response.body()
                }
            }

            override fun onFailure(
                call: Call<SearchResponse?>,
                t: Throwable
            ) {
                searchData.value = null
            }
        })
        return searchData
    }

    companion object {
        private lateinit var searchRepository: SearchRepository

        @JvmStatic
        val instance: SearchRepository?
            get() {
                searchRepository = SearchRepository()
                return searchRepository
            }
    }

}