package com.example.testnewsapp.AddressSearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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

    // some city for city selection spinner
    var cities = arrayOf("Bangalore", "Gurgaon", "Delhi", "Lucknow", "Mumbai", "Pune")
    var selectedCity: String = "Bangalore"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Address Search"

        //initialise viewModel
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchViewModel.init()

        setupRecyclerView()
        setupSpinner()

        // edit text change listner for entering text in search field
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
        // recycler view to show search result
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
        searchViewModel.getSearchRepository(query, selectedCity)
            ?.observe(this, androidx.lifecycle.Observer {
                it?.let {
                    addressList.clear()
                    addressList.addAll(it.data.addressList!!)
                    searchRecyclerAdapter!!.notifyDataSetChanged()
                    searchProgress.visibility = View.GONE
                }
            })
    }

    private fun setupSpinner() {
        // setup spinner for city change
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, cities
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    addressList.clear()
                    searchRecyclerAdapter!!.notifyDataSetChanged()
                    selectedCity = cities[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
    }

}
