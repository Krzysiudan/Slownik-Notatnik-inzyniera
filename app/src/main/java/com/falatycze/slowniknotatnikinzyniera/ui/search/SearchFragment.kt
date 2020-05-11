package com.falatycze.slowniknotatnikinzyniera.ui.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.falatycze.slowniknotatnikinzyniera.R
import com.google.android.material.textfield.TextInputEditText

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SingleRecordViewModel
    private val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        searchViewModel = ViewModelProvider(this).get(SingleRecordViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val searchEditText = root.findViewById<TextInputEditText>(R.id.textInputEditTextSearch)
        val searchButton  = root.findViewById<ImageButton>(R.id.imageButtonSearch)

        searchButton.setOnClickListener{
            val searchTag = searchEditText.text.toString()
            val action = SearchFragmentDirections.actionNavSearchToSearchResultFragment(searchTag)
            it.findNavController().navigate(action)
        }

        return root
    }


}