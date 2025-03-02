package com.example.carsdetailsrecyclerview1

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carsdetailsrecyclerview1.databinding.ActivityListViewBinding
import com.example.carsdetailsrecyclerview1.databinding.ActivityRecyclerViewBinding
import com.example.carsdetailsrecyclerview1.databinding.RecyclerRowBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecyclerViewBinding
    private lateinit var cars : ArrayList<Cars>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        var view: View = binding.root
        setContentView(view)


        cars = ArrayList<Cars>()

        var car1 = Cars("Focus", "ford", 2021, R.drawable.fordfocus)
        var car2 = Cars("Navara", "Nissan", 2022, R.drawable.nissannavara)
        var car3 = Cars("cooper", "Mini", 2020, R.drawable.minicooper)
        var car4 = Cars("duster", "Dacia", 2018, R.drawable.daciaduster)

        cars.add(car1)
        cars.add(car2)
        cars.add(car3)
        cars.add(car4)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CarAdapter(cars)
        binding.recyclerView.adapter = adapter
    }
}