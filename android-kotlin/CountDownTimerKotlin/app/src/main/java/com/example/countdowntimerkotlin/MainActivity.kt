package com.example.countdowntimerkotlin

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.countdowntimerkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
    fun onStartClick(view: View){

        binding.startButton.text = "Started! Wait for until 10 seconds!"
        binding.startButton.setEnabled(false)
        object : CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timerText.text = "Left: ${millisUntilFinished/1000}!"
            }

            override fun onFinish() {
                binding.timerText.text = "Left: 10!"
                binding.startButton.setEnabled(true)

            }

        }.start()


    }
}