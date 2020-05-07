package com.falatycze.slowniknotatnikinzyniera.ui.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.falatycze.slowniknotatnikinzyniera.R


class CategoriesListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CategoriesListAdapter.CategoryViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var categories = emptyList<String>()

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val categoryItemView: TextView = itemView.findViewById(R.id.textViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = categories[position]
        val text = "${position+1}. $currentCategory"
        holder.categoryItemView.text = text
    }

    internal fun setCategories(categories: List<String>) {
        this.categories = categories
        notifyDataSetChanged()
    }
}