package com.falatycze.slowniknotatnikinzyniera.ui.learningmode

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


private lateinit var learningModeViewModel: LearingModeViewModel
private lateinit var textViewQuestion: TextView
private lateinit var textViewAnswer: TextView
private lateinit var adapter: ViewPagerAdapter
private lateinit var fab: FloatingActionButton
private lateinit var viewPager: ViewPager2
private val ADD_RECORD_REQUEST_CODE = 100

private val TAG = "LearningModeFragment"
private val DEBUG_TAG = "LearningMode Debug"

class LearningModeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        learningModeViewModel =
            ViewModelProvider(this).get(LearingModeViewModel::class.java)
        setHasOptionsMenu(true)

        val root = inflater.inflate(R.layout.fragment_viewpager, container, false)
        viewPager = root.findViewById(R.id.viewPager)
        fab = root.findViewById(R.id.fab)

        adapter = ViewPagerAdapter()
        viewPager.adapter = adapter


        learningModeViewModel.showKnownQuestions.observe(viewLifecycleOwner, Observer { showQuestions ->
            if(showQuestions.peekContent()){
                showAllQuestions()
            } else showOnlyUnknownQuestions()
        })

        fab.setOnClickListener{questionLearned(adapter.getRecord(viewPager.currentItem))}

        return root
    }


    fun showAllQuestions(){
        learningModeViewModel.allRecords.observe(viewLifecycleOwner, Observer { records ->
            records?.let { adapter.setRecords(it) }
        })
    }

    fun showOnlyUnknownQuestions(){
        learningModeViewModel.allUnknownRecords.observe(viewLifecycleOwner, Observer { records ->
            records?.let { adapter.setRecords(it) }
        })
    }

    private fun questionLearned(currentItem: Record) {
        learningModeViewModel.viewModelScope.launch {learningModeViewModel.updateQuestionAsKnown(currentItem.id)}
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.learining_mode_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.show_learned_questions -> {
                val isActivated = item.actionView.isActivated
                learningModeViewModel.userClickOnSwitch(isActivated)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}


