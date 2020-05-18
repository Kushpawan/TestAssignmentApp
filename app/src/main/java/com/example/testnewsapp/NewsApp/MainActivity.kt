package com.example.testnewsapp.NewsApp

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
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var articleArrayList = ArrayList<NewsArticle>()
    private var newsAdapter: NewsAdapter? = null
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar?.title = "Address Search"

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.init()

        observeViewModel()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(this@MainActivity, articleArrayList)
            rvNews.layoutManager = LinearLayoutManager(this)
            rvNews.adapter = newsAdapter
            rvNews.itemAnimator = DefaultItemAnimator()
            rvNews.isNestedScrollingEnabled = true
        } else {
            newsAdapter!!.notifyDataSetChanged()
        }
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