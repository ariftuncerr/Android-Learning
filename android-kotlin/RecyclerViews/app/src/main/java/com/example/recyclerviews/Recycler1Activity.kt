package com.example.recyclerviews

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviews.adapter.FirstRVAdapter
import com.example.recyclerviews.databinding.ActivityRecycler1Binding

class Recycler1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRecycler1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecycler1Binding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

        val cityGroups = listOf(
            mutableListOf("Ankara", "İstanbul", "İzmir", "Bursa"),
            mutableListOf("Adana", "Mersin", "Konya", "Kayseri"),
            mutableListOf("Antalya","Adana","Eskişehir","Trabzon")
        )

        val adapter = FirstRVAdapter(this, cityGroups)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView.adapter = adapter



    }
}