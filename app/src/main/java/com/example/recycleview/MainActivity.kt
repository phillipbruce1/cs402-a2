package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// https://cs.boisestate.edu/~scutchin/cs402/codesnips/RecycleView.html

// DONE: refactor project to not be about coffee or koffee

// DONE: add checkmark buttons to coffee_item_view and add text into checkmark items
// use click listeners

// TODO: edit text?
// https://androidessence.com/how-to-build-a-todo-list-in-kotlin-part-2-recyclerview

// TODO: plus button in top or bottom right for add

// TODO: on long press, show pop-up dialogue to modify (join) or delete item
// concat list items with commas
// https://www.geeksforgeeks.org/how-to-detect-long-press-in-android/
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView;
    private val itemList = arrayListOf<String>();
    // insert adapter here
    // NOTE: every recycle view requires an adapter
    private val adapter: ListAdapter = ListAdapter(this, itemList);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate RecyclerView object and associate it with its view
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view);
        recyclerView.layoutManager = LinearLayoutManager(this);

        itemList.add("TODO");
        adapter.notifyItemInserted(itemList.size - 1);

        recyclerView.adapter = adapter;
    }

    fun onAddListItem(view: View) {
        itemList.add("");
        adapter.notifyItemInserted(itemList.size - 1);
    }
}