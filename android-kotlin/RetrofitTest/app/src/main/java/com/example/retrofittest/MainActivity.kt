package com.example.retrofittest

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.retrofittest.adapter.CityAdapter
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.model.CityData

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var cityAdapter : CityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view  = binding.root
        setContentView(view)

        val cityList = intent.getParcelableArrayListExtra<CityData>("city_list") ?: arrayListOf()

        // Eğer veri gelmişse RecyclerView'e bağlayacağız
        cityAdapter = CityAdapter(cityList)
        binding.recyclerViewCities.adapter = cityAdapter


    }
}