package com.example.testnewsapp.NewsApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testnewsapp.NewsApp.adapters.NewsAdapter
import com.example.testnewsapp.NewsApp.model.NewsArticle
import com.example.testnewsapp.NewsApp.viewmodels.NewsViewModel
import com.example.testnewsapp.R
import kotlinx.android.synthetic.main.activity_news_home.*
import java.util.*

class NewsHomeActivity : AppCompatActivity() {

    private var articleArrayList = ArrayList<NewsArticle>()
    private var newsAdapter: NewsAdapter? = null
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_home)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "News App"

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.init()

        observeViewModel()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(this@NewsHomeActivity, articleArrayList) {
                newsViewModel.setDetails(it)
                openDetailActivity()
            }
            rvNews.layoutManager = LinearLayoutManager(this)
            rvNews.adapter = newsAdapter
            rvNews.itemAnimator = DefaultItemAnimator()
            rvNews.isNestedScrollingEnabled = true
        } else {
            newsAdapter!!.notifyDataSetChanged()
        }
    }

    private fun openDetailActivity() {
        startActivityForResult(NewsDetailActivity.getLaunchIntent(this), 10)
    }

    private fun observeViewModel() {
        newsViewModel.getNewsRepository()?.observe(this, androidx.lifecycle.Observer {
            it?.let {
                val newsArticles = it.articles!!
                articleArrayList.addAll(newsArticles)
                newsAdapter!!.notifyDataSetChanged()
            }
        })
    }
}
