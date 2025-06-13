package com.example.androiduicomponent

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androiduicomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.switch1.setOnCheckedChangeListener{ buttonView, isChecked->
            if (isChecked) {
                Log.e("Switch Case","Switch is on")
            }
            else{
                Log.e("Switch Case","Switch is off")
            }
        }
        binding.toggleButton.setOnCheckedChangeListener { buttonView ,isChecked ->
            if (isChecked) {
                Log.e("Toggle Case","Switch is on")
            }
            else{
                Log.e("Toggle Case","Switch is off")
            }

        }
        val checkStatus = binding.switch1.isChecked


    }
}
