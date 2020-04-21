package com.falatycze.slowniknotatnikinzyniera.ui.learningmode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.Record
import com.falatycze.slowniknotatnikinzyniera.ui.base.AddQuestionFragment
import kotlinx.android.synthetic.main.fragment_viewpager.view.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.w3c.dom.Text


private lateinit var learningModeViewModel: LearingModeViewModel
private lateinit var mDetector: GestureDetectorCompat
private lateinit var textViewQuestion: TextView
private lateinit var textViewAnswer: TextView
private val ADD_RECORD_REQUEST_CODE = 100

private  val TAG = "LearningModeFragment"
private val DEBUG_TAG = "LearningMode Debug"

class LearningModeFragment : Fragment(), View.OnTouchListener{

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        learningModeViewModel =
            ViewModelProvider(this).get(LearingModeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_viewpager, container, false)
        val context = activity as Context
        val adapter  = ViewPagerAdapter()
        root.viewPager.adapter = adapter

        learningModeViewModel.allRecords.observe(viewLifecycleOwner, Observer { records ->
            records?.let { adapter.setRecords(it) }
        })


        return root
    }

    suspend fun populateTextViewsWithRandom(){
        lifecycleScope.launch{
            val randomRecord = learningModeViewModel.getRandomRecord()
            Log.i(TAG,randomRecord.toString())
            textViewQuestion.text = randomRecord?.question
            textViewAnswer.text = randomRecord?.answer
        }
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        mDetector.onTouchEvent(p1)
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }



}


