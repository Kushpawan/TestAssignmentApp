package com.example.testnewsapp.AddressSearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testnewsapp.AddressSearch.models.AddressDetail
import com.example.testnewsapp.AddressSearch.viewModel.SearchViewModel
import com.example.testnewsapp.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.search_layout.*
import java.util.*


class SearchActivity : AppCompatActivity() {

    private var searchRecyclerAdapter: SearchRecyclerAdapter? = null
    lateinit var searchViewModel: SearchViewModel
    private var addressList = ArrayList<AddressDetail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar?.title = "Address Search"

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchViewModel.init()

        setupRecyclerView()

        search_EditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().isNotEmpty()) {
                    observeViewModel(editable.toString())
                    searchProgress.visibility = View.VISIBLE
                } else {
                    searchProgress.visibility = View.GONE
                    addressList.clear()
                    searchRecyclerAdapter!!.notifyDataSetChanged()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        if (searchRecyclerAdapter == null) {
            searchRecyclerAdapter = SearchRecyclerAdapter(this, addressList)
            search_recycler.layoutManager = LinearLayoutManager(this)
            search_recycler.adapter = searchRecyclerAdapter
            search_recycler.itemAnimator = DefaultItemAnimator()
            search_recycler.isNestedScrollingEnabled = true
        } else {
            searchRecyclerAdapter!!.notifyDataSetChanged()
        }
    }


    private fun observeViewModel(query: String) {
        searchViewModel.getSearchRepository(query)
            ?.observe(this, androidx.lifecycle.Observer {
                it?.let {
                    val newsArticles = it.data.addressList!!
                    addressList.clear()
                    addressList.addAll(newsArticles)
                    searchRecyclerAdapter!!.notifyDataSetChanged()
                }
            })
    }

}
