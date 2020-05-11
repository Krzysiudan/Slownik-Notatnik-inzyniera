package com.falatycze.slowniknotatnikinzyniera.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.Record

class QuestionsListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<QuestionsListAdapter.RecordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var questions = emptyList<Record>()
    var onItemClick: ((Record) -> Unit)? = null

    inner class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textViewItem)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(questions[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return RecordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val current = questions[position]
        val text = "${current.id}. ${current.question}"
        holder.wordItemView.text = text
    }

    internal fun setRecords(questions: List<Record>) {
        this.questions = questions
        notifyDataSetChanged()
    }

    override fun getItemCount() = questions.size
}