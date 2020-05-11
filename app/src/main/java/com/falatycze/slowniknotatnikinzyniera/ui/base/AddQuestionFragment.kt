package com.falatycze.slowniknotatnikinzyniera.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.google.android.material.textfield.TextInputLayout

class AddQuestionFragment : Fragment() {

    private lateinit var baseViewModel: BaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        baseViewModel =
            ViewModelProvider(this).get(BaseViewModel::class.java)


        val root = inflater.inflate(R.layout.fragment_add_record, container, false)
        val questionInput = root.findViewById<TextInputLayout>(R.id.textInputLayoutQuestion)

        val answerInput = root.findViewById<TextInputLayout>(R.id.textInputLayoutAnswer)

        var categoryAdapter: ArrayAdapter<String>
        val categoryInput =
            root.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewCategory)
        val buttonAdd = root.findViewById<Button>(R.id.button_add)

        baseViewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            categories.let {
                categoryAdapter = ArrayAdapter<String>(
                    activity as Context,
                    android.R.layout.simple_dropdown_item_1line,
                    it
                )
                categoryInput.setAdapter(categoryAdapter)
            }
        })

        categoryInput.setOnFocusChangeListener{ view, b ->
            if(view.hasFocus()){
                categoryInput.showDropDown()
                categoryInput.threshold = 1
            }
        }

        buttonAdd.setOnClickListener {
            val question = questionInput.editText?.text.toString()
            val answer = answerInput.editText?.text.toString()
            val category = categoryInput.text.toString()
            val newRecord = Record(question, answer, category)
            baseViewModel.insert(newRecord)

            Toast.makeText(
                activity as Context,
                "Pytanie dodane! Dodaj kolejne lub wróć.",
                Toast.LENGTH_SHORT
            ).show()
        }


        return root
    }


}

private operator fun OnItemSelectedListener?.invoke(addQuestionFragment: AddQuestionFragment) {

}

