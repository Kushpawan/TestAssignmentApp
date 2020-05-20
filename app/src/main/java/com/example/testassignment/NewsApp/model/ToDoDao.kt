package com.example.testassignment.NewsApp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ToDoDao {

    @Insert
    fun insertArticles(articles: List<NewsArticle>): List<Long>

    @Query("DELETE FROM news_article")
    fun clearAllArticles()

    @Transaction
    fun clearAndCacheArticles(articles: List<NewsArticle>) {
        clearAllArticles()
        insertArticles(articles)
    }

    @Query("SELECT * FROM news_article")
    fun getNewsArticles(): LiveData<List<NewsArticle>>
}