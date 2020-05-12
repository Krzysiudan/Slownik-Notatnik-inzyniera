package com.falatycze.slowniknotatnikinzyniera.ui.singleRecord

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.falatycze.slowniknotatnikinzyniera.ui.base.AddQuestionFragment
import com.falatycze.slowniknotatnikinzyniera.ui.search.SingleRecordViewModel
import com.google.android.material.snackbar.Snackbar

class EditFragment : Fragment() {

    private lateinit var singleRecordViewModel: SingleRecordViewModel
    private lateinit var questionInput:EditText
    private lateinit var answerInput:EditText
    private lateinit var categoryInput:AutoCompleteTextView


    private val TAG = "EditFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        singleRecordViewModel =
            ViewModelProvider(activity as FragmentActivity).get(SingleRecordViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_add_record, container, false)

        questionInput = root.findViewById<EditText>(R.id.editTextAnswer)
        answerInput = root.findViewById<EditText>(R.id.editTextQuestion)

        var categoryAdapter: ArrayAdapter<String>
        categoryInput = root.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewCategory)
        val buttonAdd = root.findViewById<Button>(R.id.button_add)
        val currentRecord = singleRecordViewModel.singleRecord.value!!
        populateViewsWithCurrentRecord(currentRecord)

        categoryInput.setOnFocusChangeListener { view, b ->
            if (view.hasFocus()) {
                categoryInput.showDropDown()
                categoryInput.threshold = 1
            }
        }

        buttonAdd.setOnClickListener {
            var editedRecord = singleRecordViewModel.singleRecord.value
            editedRecord?.question = questionInput.text.toString()
           editedRecord?.answer = answerInput.text.toString()
            editedRecord?.category = categoryInput.text.toString()
            if (editedRecord != null) {
                singleRecordViewModel.updateSingleRecord(editedRecord)
                Snackbar.make(root,"Record edited!",Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }

        return root
    }

    fun populateViewsWithCurrentRecord(record: Record){
        Log.d(TAG,"Record: ${record.toString()}")
        questionInput.setText(record?.question)
        answerInput.setText(record?.answer)
        categoryInput.setText(record?.category)
    }




}



