package com.example.testassignment.NewsApp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.api.load
import com.example.testassignment.NewsApp.adapters.NewsAdapter.NewsViewHolder
import com.example.testassignment.NewsApp.model.NewsArticle
import com.example.testassignment.R
import java.util.*

class NewsAdapter(
    var context: Context,
    var articles: ArrayList<NewsArticle>,
    private val listener: (NewsArticle) -> Unit
) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        holder.tvName.text = articles[position].title
        holder.ivNews.load(articles[position].urlToImage)
        holder.newsItemCard.setOnClickListener {
            listener.invoke(articles[position])
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class NewsViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var ivNews: ImageView = itemView.findViewById(R.id.ivNews)
        var newsItemCard: CardView = itemView.findViewById(R.id.news_item_card)
    }

}