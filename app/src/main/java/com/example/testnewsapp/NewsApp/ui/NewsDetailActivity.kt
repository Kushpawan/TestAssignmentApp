package com.example.testnewsapp.NewsApp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.example.testnewsapp.NewsApp.model.NewsArticle
import com.example.testnewsapp.R
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        toolbar_title.text = "News Detail"

        val intent = intent
        if (intent.hasExtra(ARG_DETAIL)) {
            setNewsDetails(intent.getParcelableExtra(ARG_DETAIL))
        }
    }

    private fun setNewsDetails(details: NewsArticle) {
        tv_title.text = details.title
        tv_description.text = details.description
        tv_content.text = details.content


        iv_news.load(details.urlToImage)
    }

    companion object {

        const val TAG = "DetailActivity"
        private const val ARG_DETAIL = "ARG_DETAIL"

        @JvmStatic
        fun getLaunchIntent(
            context: Context,
            detail: NewsArticle
        ): Intent {
            return Intent(context, NewsDetailActivity::class.java).apply {
                this.putExtra(ARG_DETAIL, detail)
            }
        }
    }
}
