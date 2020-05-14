package com.falatycze.slowniknotatnikinzyniera.ui.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.pojo.QuestionWithId


class SingleCategoryListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SingleCategoryListAdapter.CategoryViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var questions = emptyList<QuestionWithId>()
    var onItemClick: ((QuestionWithId) -> Unit)? = null


    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val categoryItemView: TextView = itemView.findViewById(R.id.textViewItem)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(questions[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)

        return CategoryViewHolder(itemView)
    }



    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentQuestion = questions[position]
        val text = "${currentQuestion.id}. ${currentQuestion.question}"
        holder.categoryItemView.text = text
    }

    internal fun setQuestions(questions: List<QuestionWithId>) {
        this.questions = questions
        notifyDataSetChanged()
    }
}