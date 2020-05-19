package com.example.testnewsapp.AddressSearch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testnewsapp.AddressSearch.models.SearchResponse
import com.example.testnewsapp.AddressSearch.retrofit.SearchRepository

class SearchViewModel : ViewModel() {

    lateinit var searchLiveData: MutableLiveData<SearchResponse?>
    var searchRepository: SearchRepository? = null

    fun init() {
        searchRepository = SearchRepository.instance
    }

    fun getSearchRepository(query: String?, city: String): LiveData<SearchResponse?>? {
        searchLiveData = searchRepository!!.getAddressResult(query, city)
        return searchLiveData
    }
}