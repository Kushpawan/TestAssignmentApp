package com.example.testnewsapp.NewsApp.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testnewsapp.NewsApp.adapters.NewsAdapter
import com.example.testnewsapp.NewsApp.model.NewsArticle
import com.example.testnewsapp.NewsApp.viewmodels.NewsViewModel
import com.example.testnewsapp.R
import kotlinx.android.synthetic.main.activity_news_home.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class NewsHomeActivity : BaseActivity() {

    private var articleArrayList = ArrayList<NewsArticle>()
    private var newsAdapter: NewsAdapter? = null
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_home)

        toolbar_title.text = "News App"

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.init()


        observeViewModel()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(this@NewsHomeActivity, articleArrayList) {
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
        startActivityForResult(NewsDetailActivity.getLaunchIntent(this, detail), 10)
    }

    private fun observeViewModel() {
        newsViewModel.getNewsRepository()?.observe(this, androidx.lifecycle.Observer {
            it?.let {
                val newsArticles = it.articles!!
                saveNews(newsArticles)
                articleArrayList.addAll(newsArticles)
            } ?: run {
                articleArrayList.addAll(getNewsFromDb())
            }
            newsAdapter!!.notifyDataSetChanged()
        })
    }

}
