package com.falatycze.slowniknotatnikinzyniera.ui.categories

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R

class CategoriesFragment: Fragment(){

    private lateinit var categoriesViewModel: CategoriesViewModel
    private val TAG = "CategoriesFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        categoriesViewModel =
            ViewModelProvider(this).get(CategoriesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_categories, container, false)
        val context = activity as Context

        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_categories)
        val adapter = CategoriesListAdapter(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        categoriesViewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            categories?.let {
                adapter.setCategories(it)
                Log.d(TAG,"categories - observer $it")
            }

        })

        categoriesViewModel.loadCategories()

        return root
    }
}