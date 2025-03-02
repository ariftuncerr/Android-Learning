package com.example.carsdetailsrecyclerview1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.carsdetailsrecyclerview1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var cars : ArrayList<Cars>
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
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



    }
    fun goToListView ( view :View){
        var intent = Intent(applicationContext,ListViewActivity::class.java)
        startActivity(intent)

    }
    fun gotToRecyclerView ( view :View){
        var intent = Intent(applicationContext,RecyclerViewActivity::class.java)
        startActivity(intent)

    }
}