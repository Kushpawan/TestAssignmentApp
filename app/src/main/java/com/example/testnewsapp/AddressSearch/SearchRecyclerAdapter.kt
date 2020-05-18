package com.example.testnewsapp.AddressSearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testnewsapp.AddressSearch.models.AddressDetail
import com.example.testnewsapp.R
import java.util.*

class SearchRecyclerAdapter(
    var context: Context,
    var addressList: ArrayList<AddressDetail>
) : RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.search_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        holder.searchAddress.text = addressList[position].addressString
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var searchAddress: TextView = itemView.findViewById(R.id.search_result_text)

    }
}