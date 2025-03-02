package com.example.carsdetailsrecyclerview1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.carsdetailsrecyclerview1.databinding.ActivityCarsDetailBinding

class CarsDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCarsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityCarsDetailBinding.inflate(layoutInflater)
        var view: View = binding.root
        setContentView(view)
        var intent = intent

        var selectedCar = mySingleton.chosenCar


        //selected car null ise let çalışmayacak. selected car null değilse çalışacak
        selectedCar?.let { it ->
            binding.imageView.setImageResource(it.image )
            binding.imageName.text = "${selectedCar.brand} ${selectedCar.name}"
            binding.imageDetail.text = "Year: " +selectedCar.year


        }







    }
}