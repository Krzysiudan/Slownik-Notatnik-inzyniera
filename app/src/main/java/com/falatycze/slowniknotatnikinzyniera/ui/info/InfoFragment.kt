package com.falatycze.slowniknotatnikinzyniera.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.ui.search.SearchFragmentDirections
import com.falatycze.slowniknotatnikinzyniera.ui.search.SingleRecordViewModel
import com.google.android.material.textfield.TextInputEditText

class InfoFragment : Fragment() {

    private val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        val root = inflater.inflate(R.layout.fragment_info, container, false)


        return root
    }


}