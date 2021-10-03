package com.example.recycleview

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// https://cs.boisestate.edu/~scutchin/cs402/codesnips/RecycleView.html

// DONE: refactor project to not be about coffee or koffee

// DONE: add checkmark buttons to coffee_item_view and add text into checkmark items
// use click listeners

// DONE: edit text?
// https://androidessence.com/how-to-build-a-todo-list-in-kotlin-part-2-recyclerview

// DONE: checking item crosses it out and moves it to bottom of list

// DONE: save new text after being added

// DONE: plus button in top or bottom right for add

// TODO: add toolbar menu to join, split, or delete item
// concat and split list items via commas

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView;
    private val itemList = arrayListOf<Task>();

    // insert adapter here
    // NOTE: every recycle view requires an adapter
    private val adapter: ListAdapter = ListAdapter(this, itemList);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate RecyclerView object and associate it with its view
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(this);

        recyclerView.adapter = adapter;
    }


    /**
     * Generates a unique id to link list items in the arrayList and within the UI
     */
    private fun generateTag(): String {
        return System.currentTimeMillis().toString();
    }

    fun onAddListItem(view: View) {
        itemList.add(0, Task(generateTag()));
        adapter.notifyItemInserted(0);
    }

    /**
     * Reflects checkbox actions in itemList and moves list entry accordingly
     */
    fun onPressCheckbox(view: View) {
        // get the text box
        if (view is CheckBox) {
            val parent = view.parent;
            if (parent is View) {
                val textBox = parent.findViewById<TextView>(R.id.itemText);
                // update itemList
                val i = itemList.indexOf(itemList.find { it.tag == textBox.tag })
                itemList[i].completed = view.isChecked;
                if (view.isChecked) {
                    // cross out text
                    textBox.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG;
                    // move Task to bottom of the list
                    val item = itemList.removeAt(i);
                    itemList.add(item);
                    adapter.notifyItemMoved(i, itemList.size - 1);
                } else {
                    // remove text strikethrough
                    textBox.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv();
                    // move Task to top of list
                    val item = itemList.removeAt(i);
                    itemList.add(0, item);
                    adapter.notifyItemMoved(i, 0);
                }
            }
        }
    }
}