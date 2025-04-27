package com.example.retrofittest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
                intent.putParcelableArrayListExtra("city_list", ArrayList(response.data)) // Listeyi doğrudan gönder
                startActivity(intent)
                finish()
            }
            cityViewModel.isError.observe(this){ error->
                if (error){
                    Toast.makeText(this,"Veri alınırken Hata Oluştu, İnternet bağlantınızı kontrol edin",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
