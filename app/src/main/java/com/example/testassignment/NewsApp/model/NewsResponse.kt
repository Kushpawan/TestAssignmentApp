package com.example.testassignment.NewsApp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse(
    var status: String? = null,
    var totalResults: Int? = null,
    var articles: List<NewsArticle>? = null
) : Parcelable

@Parcelize
data class NewsArticle(
    var source: NewsSource? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
) : Parcelable

@Parcelize
data class NewsSource(
    var id: String? = null,
    var name: String? = null
) : Parcelable