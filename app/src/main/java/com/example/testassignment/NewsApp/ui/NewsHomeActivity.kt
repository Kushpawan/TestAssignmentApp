package com.example.testassignment.NewsApp.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testassignment.NewsApp.adapters.NewsAdapter
import com.example.testassignment.NewsApp.model.AppDatabase
import com.example.testassignment.NewsApp.model.NewsArticle
import com.example.testassignment.NewsApp.viewmodels.NewsViewModel
import com.example.testassignment.R
import kotlinx.android.synthetic.main.activity_news_home.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class NewsHomeActivity : BaseActivity() {

    private var articleArrayList = ArrayList<NewsArticle>()
    private var newsAdapter: NewsAdapter? = null
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_home)
        db = AppDatabase(this)

        toolbar_title.text = "News App"

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.init()

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter =
                NewsAdapter(
                    this@NewsHomeActivity,
                    articleArrayList
                ) {
                    openDetailActivity(it)
                }
            rvNews.layoutManager = LinearLayoutManager(this)
            rvNews.adapter = newsAdapter
            rvNews.itemAnimator = DefaultItemAnimator()
            rvNews.isNestedScrollingEnabled = true
        } else {
            newsAdapter!!.notifyDataSetChanged()
        }
    }

    private fun openDetailActivity(detail: NewsArticle) {
        startActivityForResult(
            NewsDetailActivity.getLaunchIntent(
                this,
                detail
            ), 10
        )
    }

    private fun observeViewModel() {
        newsViewModel.getNewsRepository()?.observe(this, androidx.lifecycle.Observer {
            it?.let {
                val articles = it.articles!!
                // saveNews(newsArticles)
                save(articles)
                articleArrayList.addAll(articles)
                newsAdapter!!.notifyDataSetChanged()
            } ?: run {
                updateFromBd()
            }
        })
    }

    private fun save(newsArticles: List<NewsArticle>) {
        GlobalScope.launch {
            db.todoDao().clearAndCacheArticles(newsArticles)
        }
    }

    private fun updateFromBd() {
       db.todoDao().getNewsArticles().observe(this, androidx.lifecycle.Observer {
            it.let { it1 ->
                articleArrayList.addAll(it1)
                newsAdapter!!.notifyDataSetChanged()
            }
        })
    }
}
