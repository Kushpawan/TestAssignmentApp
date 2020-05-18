package com.example.testnewsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testnewsapp.adapters.NewsAdapter
import com.example.testnewsapp.model.NewsArticle
import com.example.testnewsapp.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var articleArrayList = ArrayList<NewsArticle>()
    private var newsAdapter: NewsAdapter? = null
    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                val newsArticles = it?.articles!!
                articleArrayList.addAll(newsArticles)
                newsAdapter!!.notifyDataSetChanged()
            }
        })
    }
}
