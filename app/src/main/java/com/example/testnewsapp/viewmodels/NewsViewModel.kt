package com.example.testnewsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testnewsapp.NewsRepository
import com.example.testnewsapp.NewsRepository.Companion.instance
import com.example.testnewsapp.model.NewsResponse

class NewsViewModel : ViewModel() {

    lateinit var mutableLiveData: MutableLiveData<NewsResponse?>
    var newsRepository: NewsRepository? = null

    fun init() {
        newsRepository = instance
        mutableLiveData = newsRepository!!.getNews("in", "a0a21a67f3b24f4696d67341ab342d54")
    }

    fun getNewsRepository(): LiveData<NewsResponse?>? {
        return mutableLiveData
    }
}