package com.example.testassignment.NewsApp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "news_article")
@Parcelize
data class NewsArticle(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
) : Parcelable