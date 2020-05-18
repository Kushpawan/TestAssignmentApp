package com.example.testnewsapp.NewsApp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsSource {
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

}