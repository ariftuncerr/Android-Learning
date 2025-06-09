package com.example.retrofittest.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.retrofittest.viewmodel.CityViewModel

class SplashActivity : AppCompatActivity() {
    private val cityViewModel: CityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        observeViewModel()


    }
    private fun observeViewModel(){
        cityViewModel.fetchCities(1)
        cityViewModel.cityResponse.observe(this) { response ->
            if (response != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("city_response",response) // Listeyi doğrudan gönder
                startActivity(intent)
                finish()
            }
            cityViewModel.isError.observe(this){ error->
                if (error)
                    Toast.makeText(this,"Veri alınırken Hata Oluştu, İnternet bağlantınızı kontrol edin",Toast.LENGTH_SHORT).show()

            }
        }
    }
}
