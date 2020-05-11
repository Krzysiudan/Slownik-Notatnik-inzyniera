package com.falatycze.slowniknotatnikinzyniera.ui.base
import android.content.Context

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BaseFragment : Fragment() {

    private lateinit var baseViewModel: BaseViewModel
    private val ADD_RECORD_REQUEST_CODE = 100



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseViewModel =
            ViewModelProvider(this).get(BaseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_base, container, false)

        val context = activity as Context
         val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = QuestionsListAdapter(context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter.onItemClick = {record ->
            Toast.makeText(activity as Context,"On item clicked ${record.question}", Toast.LENGTH_SHORT).show()
        }

        baseViewModel.allRecords.observe(viewLifecycleOwner, Observer { records ->
            records?.let { adapter.setRecords(it) }
        })

        val fab = root.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            launchAddRecordFragment()
        }

        return root
    }

    private fun launchAddRecordFragment(){
        this.findNavController().navigate(R.id.nav_add_record)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
}
