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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R
import kotlinx.android.synthetic.main.fragment_base.*

class SingleCategoryFragment: Fragment(){

    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var recyclerView: RecyclerView
    private val TAG = "CategoriesFragment"
    private val args: SingleCategoryFragmentArgs by navArgs()


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
         recyclerView.layoutManager = LinearLayoutManager(activity as Context)
         val adapter = SingleCategoryListAdapter(activity as Context)
         recyclerView.adapter = adapter

         val currentCategory = args.category

         adapter.onItemClick = {question ->
                val action = SingleCategoryFragmentDirections.actionSingleCategoryFragmentToSingleRecordFragment(question.id)
             findNavController().navigate(action)
         }

         categoriesViewModel.questionsFromSingleCategory.observe(viewLifecycleOwner, Observer { questions ->
             questions?.let {
                 adapter.setQuestions(it)
                 Log.d(TAG,"categories - observer $it")
             }
         })
         categoriesViewModel.loadQuestionsFromSingleCategory(currentCategory)



     }
}