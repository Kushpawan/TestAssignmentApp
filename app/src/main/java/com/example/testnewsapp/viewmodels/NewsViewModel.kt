package com.example.testnewsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testnewsapp.NewsRepository
import com.example.testnewsapp.NewsRepository.Companion.instance
import com.example.testnewsapp.model.NewsResponse

class NewsViewModel : ViewModel() {

    private var mutableLiveData: MutableLiveData<NewsResponse?>? = null
    var newsRepository: NewsRepository? = null
    fun init() {
        if (mutableLiveData != null) {
            return
        }
        newsRepository = instance
        mutableLiveData = newsRepository!!.getNews("google-news", "a0a21a67f3b24f4696d67341ab342d54")
    }

    fun getNewsRepository(): LiveData<NewsResponse?>? {
        return mutableLiveData
    }
}