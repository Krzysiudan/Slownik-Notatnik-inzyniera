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

class EditFragment : Fragment() {

    private lateinit var singleRecordViewModel: SingleRecordViewModel
    private val TAG = "EditFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        singleRecordViewModel =
            ViewModelProvider(activity as FragmentActivity).get(SingleRecordViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_add_record, container, false)

        val questionInput = root.findViewById<EditText>(R.id.editTextAnswer)
        val answerInput = root.findViewById<EditText>(R.id.editTextQuestion)

        var categoryAdapter: ArrayAdapter<String>
        val categoryInput =
            root.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewCategory)
        val buttonAdd = root.findViewById<Button>(R.id.button_add)
        val record = singleRecordViewModel.singleRecord.value
        Log.d(TAG,"Record: ${record.toString()}")
        questionInput.setText(record?.question)
        answerInput.setText(record?.answer)
        categoryInput.setText(record?.category)


        categoryInput.setOnFocusChangeListener { view, b ->
            if (view.hasFocus()) {
                categoryInput.showDropDown()
                categoryInput.threshold = 1
            }
        }

        buttonAdd.setOnClickListener {
            val question = questionInput.text.toString()
            val answer = answerInput.text.toString()
            val category = categoryInput.text.toString()
            val newRecord = Record(question, answer, category)
            singleRecordViewModel.updateSingleRecord(newRecord)
            Toast.makeText(
                activity as Context, "Pytanie edytowane!", Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }

        return root
    }


}

private operator fun OnItemSelectedListener?.invoke(addQuestionFragment: AddQuestionFragment) {

}

