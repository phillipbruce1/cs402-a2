package com.example.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(context: Context, private var itemList: ArrayList<String>) :
    RecyclerView.Adapter<ListAdapter.ListItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ListAdapter.ListItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_view, parent, false)
        return ListItemHolder(view)
    }

    override fun getItemCount() = itemList.size

    /**
     * Executed when binding the list item to the View
     */
    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            itemView.findViewById<TextView>(R.id.textView).apply {
                text = item;
            };
        };
    }

    class ListItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById<RelativeLayout>(R.id.list_item).findViewById<TextView>(R.id.textView);
    }
}