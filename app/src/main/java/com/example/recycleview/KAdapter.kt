package com.example.recycleview

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(context: Context, private var itemList: ArrayList<Task>) :
    RecyclerView.Adapter<ListAdapter.ListItemHolder>(), View.OnLongClickListener, TextWatcher {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ListAdapter.ListItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_view, parent, false)
        val checkbox = view.findViewById<CheckBox>(R.id.checkBox);
        checkbox.setOnLongClickListener(this);
        view.findViewById<EditText>(R.id.itemText).addTextChangedListener(this);
        return ListItemHolder(view)
    }

    override fun getItemCount() = itemList.size

    /**
     * Executed when binding the list item to the View
     */
    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            itemView.findViewById<TextView>(R.id.itemText).apply {
                tag = item.tag;
            };
        };
    }

    override fun onLongClick(view: View): Boolean {
        val parent = view.parent;
        if (parent !is View)
            return false;
        val textBox = parent.findViewById<TextView>(R.id.itemText);
        var selected = itemList.find { it.tag == textBox.tag }?.selected;
        if (selected !is Boolean)
            return false;
        selected = !selected;
        itemList.find { it.tag == textBox.tag }?.selected = selected;
        var color = "#ffffff";
        if (selected)
            color = "#58bce8";
        parent.setBackgroundColor(Color.parseColor(color));
        return true;
    }

    class ListItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView =
            view.findViewById<RelativeLayout>(R.id.list_item).findViewById<TextView>(R.id.itemText);
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {
        if (p0 !is EditText)
            return;
        itemList.find { it.tag == p0.tag }?.text = p0.text.toString();
    }
}