package com.example.testassignment.NewsApp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testassignment.NewsApp.model.NewsArticle
import com.example.testassignment.NewsApp.model.NewsResponse
import com.example.testassignment.NewsApp.retrofit.NewsRepository
import com.example.testassignment.NewsApp.retrofit.NewsRepository.Companion.instance

class NewsViewModel : ViewModel() {

    lateinit var mutableLiveData: MutableLiveData<NewsResponse?>
    var newsRepository: NewsRepository? = null

    fun init() {
        newsRepository = instance
        mutableLiveData = newsRepository!!.getNews("us", "a0a21a67f3b24f4696d67341ab342d54")
    }

    fun getNewsRepository(): LiveData<NewsResponse?>? {
        return mutableLiveData
    }

}