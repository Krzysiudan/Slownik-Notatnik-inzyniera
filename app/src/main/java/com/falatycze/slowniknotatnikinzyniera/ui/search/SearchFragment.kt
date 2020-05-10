package com.falatycze.slowniknotatnikinzyniera.ui.search

import com.falatycze.slowniknotatnikinzyniera.ui.categories.CategoriesViewModel


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.falatycze.slowniknotatnikinzyniera.R
import com.google.android.material.textfield.TextInputLayout

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val context = activity as Context
        val searchTextView = root.findViewById<TextInputLayout>(R.id.textInputLayoutSearch)
        val searchButton  = root.findViewById<ImageButton>(R.id.imageButtonSearch)

        searchButton.setOnClickListener{
            val searchTag = searchTextView.editText.toString()
            val action = SearchFragmentDirections.actionNavSearchToSearchResultFragment(searchTag)
            it.findNavController().navigate(action)
        }

        return root
    }


}