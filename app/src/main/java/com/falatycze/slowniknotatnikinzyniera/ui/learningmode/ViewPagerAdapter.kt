package com.falatycze.slowniknotatnikinzyniera.ui.learningmode

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.Record
import kotlinx.android.synthetic.main.fragment_learning.view.*

class ViewPagerAdapter(): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {


    private var questions = emptyList<Record>() // Cached copy of words
    private val TAG = "LearningMode"


    inner class ViewPagerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_learning,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val randomRecord = questions[position]
        holder.itemView.textViewAnswer.text = randomRecord.answer
        holder.itemView.textViewQuestion.text = randomRecord.question
        questions.indexOf(randomRecord)
        Log.d(TAG,"ViewPager On Bind View Holder, random Record: $randomRecord ")
    }

    internal fun setRecords(questions: List<Record>) {
        this.questions = questions
        notifyDataSetChanged()
    }
}