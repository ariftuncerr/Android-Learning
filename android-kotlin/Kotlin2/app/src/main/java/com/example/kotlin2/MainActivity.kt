package com.example.kotlin2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var p1 = Person("Arif",22,"Student")
        var p2 = Person("Ali",25,"Engineer")
        var p3 = Person("Veli",30,"Doctor")

        val personNameList = listOf(p1.name,p2.name,p3.name)

        val list = findViewById<ListView>(R.id.listView)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,personNameList)

        list.adapter = adapter





    }
}