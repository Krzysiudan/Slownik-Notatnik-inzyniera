package com.falatycze.slowniknotatnikinzyniera.ui.learningmode

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.CompoundButton
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


private lateinit var learningModeViewModel: LearingModeViewModel
private lateinit var adapter: ViewPagerAdapter
private lateinit var fab: FloatingActionButton
private lateinit var viewPager: ViewPager2
private lateinit var observerUnknownQuestions: Observer<List<Record>>
private lateinit var observerQuestions: Observer<List<Record>>
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
        observerQuestions = Observer{ records -> records?.let{ adapter.setRecords(it)}}


        learningModeViewModel.showKnownQuestions.observe(viewLifecycleOwner, Observer { showQuestions ->
            Log.d(DEBUG_TAG,"ShowKnownQuestions observer")
            if(showQuestions.peekContent()){
                showAllQuestions()
                Snackbar.make(root,getString(R.string.snackbar_all_questions),Snackbar.LENGTH_SHORT).show()
            } else {
                showOnlyUnknownQuestions()
                Snackbar.make(root,getString(R.string.snackbar_unknown_questions),Snackbar.LENGTH_SHORT).show()

            }
        })

        fab.setOnClickListener{
            questionLearned(adapter.getRecord(viewPager.currentItem))
            Snackbar.make(root,"Question marked as learned",Snackbar.LENGTH_SHORT).show()
        }

        return root
    }


    fun showAllQuestions(){
        if(learningModeViewModel.allUnknownRecords.hasObservers()){
            learningModeViewModel.allUnknownRecords.removeObserver(observerQuestions)
        }
        learningModeViewModel.allRecords.observe(viewLifecycleOwner, observerQuestions)

        Log.d(DEBUG_TAG,"All questions showing")
    }

    fun initializeAllQuestionsObserver(){
    }

    fun showOnlyUnknownQuestions(){
        if(learningModeViewModel.allRecords.hasObservers()){
            learningModeViewModel.allRecords.removeObserver(observerQuestions)
        }
        learningModeViewModel.allUnknownRecords.observe(viewLifecycleOwner, observerQuestions)
        Log.d(DEBUG_TAG,"All unknown questions showing")

    }

    private fun questionLearned(currentItem: Record) {
        learningModeViewModel.viewModelScope.launch {learningModeViewModel.updateQuestionAsKnown(currentItem.id)}
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.learining_mode_menu,menu)
        val item = menu.findItem(R.id.show_learned_questions)
        item.setActionView(R.layout.switch_item)

        val switch = item.actionView.findViewById<Switch>(R.id.switchLearningMode)
        switch.isChecked = true
        switch.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                val isActivated = p1
                Log.d(DEBUG_TAG, "fun onOptionsItemSelected, switch isActivated : $p1")
                learningModeViewModel.userClickOnSwitch(isActivated)
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(DEBUG_TAG,"onOptionsItemSelected")
        return when (item.itemId) {
            R.id.show_learned_questions -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}


