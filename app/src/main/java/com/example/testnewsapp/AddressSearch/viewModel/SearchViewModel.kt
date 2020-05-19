package com.example.testnewsapp.AddressSearch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testnewsapp.AddressSearch.models.SearchResponse
import com.example.testnewsapp.AddressSearch.retrofit.SearchRepository

class SearchViewModel : ViewModel() {

    lateinit var searchLiveData: MutableLiveData<SearchResponse?>
    lateinit var searchRepository: SearchRepository

    fun init() {
        searchRepository = SearchRepository.instance!!
    }

    fun getSearchRepository(query: String?, city: String): LiveData<SearchResponse?>? {
        searchLiveData = searchRepository.getAddressResult(query, city)
        return searchLiveData
    }
}