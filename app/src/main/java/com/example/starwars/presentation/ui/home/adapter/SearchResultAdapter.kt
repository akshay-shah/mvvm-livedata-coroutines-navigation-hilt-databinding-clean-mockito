package com.example.starwars.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R

class SearchResultAdapter(
    private val onClickListener: (position: Int) -> Unit
) :
    RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    private var characterNameList: List<String>? = emptyList()

    class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textViewName)
    }

    fun setList(list: List<String>?) {
        characterNameList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_list_view, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.textView.text = characterNameList?.get(position) ?: ""
        holder.textView.setOnClickListener {
            onClickListener.invoke(holder.bindingAdapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return characterNameList?.size ?: 0
    }

}