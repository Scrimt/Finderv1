package com.example.finderv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.random.Random

class Home : AppCompatActivity(), ExampleAdapter.OnItemClickListener{

    private val exampleList = generateDummyList(20)
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val buttonProfil = findViewById<Button>(R.id.ProfilButton)
        buttonProfil.setOnClickListener{
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }

        val recycler = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_view)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
    }


    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]
        adapter.notifyItemChanged(position)

    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.test2
                1 -> R.drawable.test3
                else -> R.drawable.test4
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }

}