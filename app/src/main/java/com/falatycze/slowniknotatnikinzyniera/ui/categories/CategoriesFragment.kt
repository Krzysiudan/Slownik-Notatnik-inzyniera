package com.falatycze.slowniknotatnikinzyniera.ui.categories

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R
import kotlinx.android.synthetic.main.fragment_base.*

class CategoriesFragment: Fragment(){

    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var recyclerView: RecyclerView
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
        recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_categories)
        setupRecyclerAdapter()
        return root
    }

     fun setupRecyclerAdapter(){
         recyclerView.layoutManager = LinearLayoutManager(context)
         val adapter = CategoriesListAdapter(activity as Context)
         recyclerView.adapter = adapter

         adapter.onItemClick = {category ->
                Toast.makeText(activity as Context,"On item clicked $category",Toast.LENGTH_SHORT).show()
         }

         categoriesViewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
             categories?.let {
                 adapter.setCategories(it)
                 Log.d(TAG,"categories - observer $it")
             }

         })
         categoriesViewModel.loadCategories()



     }
}