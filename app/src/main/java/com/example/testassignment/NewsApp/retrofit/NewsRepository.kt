package com.example.testassignment.NewsApp.retrofit

import androidx.lifecycle.MutableLiveData
import com.example.testassignment.NewsApp.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    private val newsApi: NewsApi =
        RetrofitService.createService(
            NewsApi::class.java
        )
    val newsData = MutableLiveData<NewsResponse?>()

    fun getNews(country: String?, key: String?): MutableLiveData<NewsResponse?> {
        newsApi.getNewsList(country, key)!!.enqueue(object : Callback<NewsResponse?> {
            override fun onResponse(
                call: Call<NewsResponse?>,
                response: Response<NewsResponse?>
            ) {
                if (response.isSuccessful) {
                    newsData.value = response.body()
                }
            }

            override fun onFailure(
                call: Call<NewsResponse?>,
                t: Throwable
            ) {
                newsData.value = null
            }
        })
        return newsData
    }

    companion object {
        private lateinit var newsRepository: NewsRepository

        @JvmStatic
        val instance: NewsRepository?
            get() {
                newsRepository =
                    NewsRepository()
                return newsRepository
            }
    }

}