package com.example.carsdetailsrecyclerview1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.carsdetailsrecyclerview1.databinding.ActivityListViewBinding
import com.example.carsdetailsrecyclerview1.databinding.ActivityMainBinding

class ListViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListViewBinding
    private lateinit var cars : ArrayList<Cars>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityListViewBinding.inflate(layoutInflater)
        var view : View = binding.root
        setContentView(view)


        cars  = ArrayList<Cars>()

        var car1 = Cars("Focus","ford",2021,R.drawable.fordfocus)
        var car2 = Cars("Navara","Nissan",2022,R.drawable.nissannavara)
        var car3 = Cars("cooper","Mini",2020,R.drawable.minicooper)
        var car4 = Cars("duster","Dacia", 2018,R.drawable.daciaduster)

        cars.add(car1)
        cars.add(car2)
        cars.add(car3)
        cars.add(car4)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,cars.map{cars->cars.name})
        binding.listView.adapter = adapter

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener({parent, view, position, id ->
            val intent = Intent (ListViewActivity@this,CarsDetailActivity::class.java)
            intent.putExtra("car",cars.get(position))
            startActivity(intent)

        })



    }
}