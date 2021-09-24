package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: refactor project to not be about coffee or koffee

// TODO: add checkmark buttons to coffee_item_view and add text into checkmark items
// use click listeners

// TODO: plus button in top or bottom right for add

// TODO: on long press, show pop-up dialogue to modify or delete item
// https://www.geeksforgeeks.org/how-to-detect-long-press-in-android/
class MainActivity : AppCompatActivity() {
    private lateinit var kRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // instantiate RecyclerView object and associate it with its view
        kRecyclerView = findViewById<RecyclerView>(R.id.coffee_recycler_view)
        kRecyclerView.layoutManager = LinearLayoutManager(this)

        // instantiate hardcoded arraylist
        val koffeeList = arrayListOf<String>("Arabic", "Robusta", "Sumatra", "Kona")

        // insert adapter here
        // NOTE: every recycle view requires an adapter
        val kadapter: KAdapter = KAdapter(this, koffeeList);

        kRecyclerView.adapter = kadapter;
    }
}