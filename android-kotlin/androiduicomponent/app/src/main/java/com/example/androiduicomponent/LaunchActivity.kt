package com.example.androiduicomponent

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiduicomponent.databinding.ActivityLaunchBinding

class LaunchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLaunchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)

    }
    fun goToDatePickerPage (view: View){
        val intent = Intent(this, DateAndTimePickerActivity :: class.java)
        startActivity(intent)
    }
    fun goToImageAndVideoPage (view : View) {
        val intent = Intent(this, DateAndTimePickerActivity :: class.java)
        startActivity(intent)
    }
    fun goToBasicComponentsPage (view: View){
        val intent = Intent(this, BasicComponentActivity :: class.java)
        startActivity(intent)

    }
}