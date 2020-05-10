package com.falatycze.slowniknotatnikinzyniera.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R
import com.falatycze.slowniknotatnikinzyniera.database.Record


class SearchResultListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SearchResultListAdapter.CategoryViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var searchResult = emptyList<Record>()
    var onItemClick: ((Record) -> Unit)? = null


    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val categoryItemView: TextView = itemView.findViewById(R.id.textViewItem)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(searchResult[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)

        return CategoryViewHolder(itemView)
    }



    override fun getItemCount(): Int = searchResult.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentRecord = searchResult[position]
        val text = "${currentRecord.id}. ${currentRecord.question}"
        holder.categoryItemView.text = text
    }

    internal fun setResaults(resaults: List<Record>) {
        this.searchResult = resaults
        notifyDataSetChanged()
    }
}