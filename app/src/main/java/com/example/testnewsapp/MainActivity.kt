package com.example.testnewsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testnewsapp.adapters.NewsAdapter
import com.example.testnewsapp.model.NewsArticle
import com.example.testnewsapp.model.NewsResponse
import com.example.testnewsapp.viewmodels.NewsViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private var articleArrayList = ArrayList<NewsArticle>()
    private var newsAdapter: NewsAdapter? = null
    private var rvHeadline: RecyclerView? = null
    private var newsViewModel: NewsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvHeadline = findViewById(R.id.rvNews)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel!!.init()
        newsViewModel!!.getNewsRepository()?.observe(this, androidx.lifecycle.Observer {
            val newsArticles = it?.articles!!
            articleArrayList.addAll(newsArticles)
            newsAdapter!!.notifyDataSetChanged()
        })
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(this@MainActivity, articleArrayList)
            rvHeadline!!.layoutManager = LinearLayoutManager(this)
            rvHeadline!!.adapter = newsAdapter
            rvHeadline!!.itemAnimator = DefaultItemAnimator()
            rvHeadline!!.isNestedScrollingEnabled = true
        } else {
            newsAdapter!!.notifyDataSetChanged()
        }
    }
}
