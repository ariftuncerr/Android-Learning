package com.example.runnablekotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.runnablekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var runnable : Runnable = Runnable {}
    var handler : Handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun onStart(view: View){
        var number =0
        runnable = object : Runnable{
            override fun run() {
                number++
                binding.textView.text = "Time: $number"
                handler.postDelayed(this,1000)


            }
        }
        handler.post(runnable)
        binding.Start.isEnabled = false

    }
    fun onStop(view:View){
        handler.removeCallbacks(runnable)
        binding.Start.isEnabled = true


    }
}