package com.falatycze.slowniknotatnikinzyniera.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.BaseViewModel
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.google.android.material.textfield.TextInputLayout

class AddQuestionFragment : Fragment(), OnItemSelectedListener {

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
        val spinnerCatergory = root.findViewById<Spinner>(R.id.spinner)
        val buttonAdd = root.findViewById<Button>(R.id.button_add)

        var listOfItems = arrayOf("Automatyka","Mechanika","Robotyka")
        spinnerCatergory.onItemSelectedListener(this)
        val arrayAdapter = ArrayAdapter<String>(activity as Context, android.R.layout.simple_spinner_item,listOfItems)

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCatergory.adapter = (arrayAdapter)

        buttonAdd.setOnClickListener{
            val question = questionInput.editText?.text.toString()
            val answer = answerInput.editText?.text.toString()
            val category = spinnerCatergory.selectedItem.toString()
            val newRecord = Record(question,answer, category)
            baseViewModel.insert(newRecord)

            Toast.makeText(activity as Context, "Pytanie dodane! Dodaj kolejne lub wróć.",Toast.LENGTH_SHORT).show()
        }


        return root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }


}

private operator fun OnItemSelectedListener?.invoke(addQuestionFragment: AddQuestionFragment) {

}

