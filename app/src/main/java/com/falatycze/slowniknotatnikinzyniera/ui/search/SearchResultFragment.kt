package com.falatycze.slowniknotatnikinzyniera.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R

class SearchResultFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private val TAG = "SearchFragment"
    val args: SearchResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search_result, container, false)
        val context = activity as Context

        val searchTag = args.searchTag
        val textViewNumberOfOccurrences = root.findViewById<TextView>(R.id.textView_occurrences)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_search_reasult)
        val adapter = SearchResultListAdapter(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        searchViewModel.results.observe(viewLifecycleOwner, Observer { records->
            adapter.setResaults(records)
            Log.d(TAG,"Results observer, records: ${records.toString()}")
            val text = "Number of occurrences :${adapter.itemCount}"
            textViewNumberOfOccurrences.text = text
        })


        searchViewModel.loadResults(searchTag)

        return root
    }
}