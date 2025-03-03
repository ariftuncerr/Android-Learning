package com.example.playoffs.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.playoffs.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)



    }
    fun player2MatchClick(view: View){
        val intent = Intent(this, Player2MatchActivity::class.java)
        startActivity(intent)
    }
}