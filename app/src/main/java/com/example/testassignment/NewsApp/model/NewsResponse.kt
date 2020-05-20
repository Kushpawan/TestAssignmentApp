package com.example.testassignment.NewsApp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<NewsArticle>? = null
) : Parcelable