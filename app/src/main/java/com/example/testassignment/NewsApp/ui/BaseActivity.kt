package com.example.testassignment.NewsApp.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testassignment.NewsApp.model.AppDatabase
import com.example.testassignment.NewsApp.model.NewsArticle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class BaseActivity : AppCompatActivity() {

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "news"
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    fun saveNews(newsArticles: List<NewsArticle>) {
        val editor = sharedPref.edit()
        val json = Gson().toJson(newsArticles)
        editor.putString(PREF_NAME, json)
        editor.apply()
    }

    fun getNewsFromDb(): Collection<NewsArticle> {
        val jsonStr = sharedPref.getString(PREF_NAME, null)
        val type = object : TypeToken<List<NewsArticle>>() {
        }.type
        jsonStr.let {
            return Gson().fromJson<List<NewsArticle>>(it, type)
        }

    }
}