package com.example.testnewsapp

import androidx.lifecycle.MutableLiveData
import com.example.testnewsapp.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    private val newsApi: NewsApi = RetrofitService.createService(NewsApi::class.java)

    fun getNews(source: String?, key: String?): MutableLiveData<NewsResponse?> {
        val newsData = MutableLiveData<NewsResponse?>()
        newsApi.getNewsList(source, key)!!.enqueue(object : Callback<NewsResponse?> {
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
        private var newsRepository: NewsRepository? = null

        @JvmStatic
        val instance: NewsRepository?
            get() {
                if (newsRepository == null) {
                    newsRepository = NewsRepository()
                }
                return newsRepository
            }
    }

}