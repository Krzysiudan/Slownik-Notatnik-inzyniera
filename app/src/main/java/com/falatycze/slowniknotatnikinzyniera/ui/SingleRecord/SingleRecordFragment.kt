package com.falatycze.slowniknotatnikinzyniera.ui.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.falatycze.slowniknotatnikinzyniera.R
import com.google.android.material.textfield.TextInputEditText

class SingleRecordFragment : Fragment() {

    private lateinit var singleRecordViewModel: SingleRecordViewModel
    private val TAG = "SingleRecordFragment"
    val args: SingleRecordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        singleRecordViewModel = ViewModelProvider(this).get(SingleRecordViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_single_record, container, false)

        val textViewQuestion = root.findViewById<TextView>(R.id.textViewQuestion)
        val textViewAnswer = root.findViewById<TextView>(R.id.textViewAnswer)
        val singleRecordId = args.idRecord

        singleRecordViewModel.singleRecord.observe(viewLifecycleOwner, Observer { record ->
            textViewQuestion.text = record.question
            textViewAnswer.text = record.answer
        })

        singleRecordViewModel.loadSingleRecord(singleRecordId)



        return root
    }


}