package com.example.testnewsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.testnewsapp.R
import com.example.testnewsapp.adapters.NewsAdapter.NewsViewHolder
import com.example.testnewsapp.model.NewsArticle
import com.squareup.picasso.Picasso
import java.util.*

class NewsAdapter(
    var context: Context,
    var articles: ArrayList<NewsArticle>
) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.tvName.text = articles[position].title.toString()
        holder.tvDesCription.text = articles[position].toString()
        Picasso.get().load(articles[position].urlToImage).into(holder.ivNews)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class NewsViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvDesCription: TextView = itemView.findViewById(R.id.tvDesCription)
        var ivNews: ImageView = itemView.findViewById(R.id.ivNews)

    }

}