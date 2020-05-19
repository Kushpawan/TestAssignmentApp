package com.example.testnewsapp.NewsApp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testnewsapp.NewsApp.model.NewsArticle
import com.example.testnewsapp.NewsApp.viewmodels.NewsViewModel
import com.example.testnewsapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "News Detail"

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.init()

        observeViewModel()
    }

    private fun observeViewModel() {
        newsViewModel.getDetails().observe(this, androidx.lifecycle.Observer {
            setNewsDetails(it!!)
        })
    }

    private fun setNewsDetails(details: NewsArticle) {
        tv_title.text = details.title
        tv_description.text = details.description
       // tv_content.text = details.content


        Picasso.get().load(details.urlToImage).into(iv_news)
    }

    companion object {

        const val TAG = "DetailActivity"

        @JvmStatic
        fun getLaunchIntent(
            context: Context
        ): Intent {
            return Intent(context, NewsDetailActivity::class.java)
        }
    }
}
