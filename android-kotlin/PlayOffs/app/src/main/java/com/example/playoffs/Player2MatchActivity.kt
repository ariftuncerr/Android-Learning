package com.example.playoffs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.playoffs.databinding.ActivityPlayer2MatchBinding

class Player2MatchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlayer2MatchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayer2MatchBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        var team1 = Team ("Liverpool",R.drawable.liverpool, 300,"Premier League")
        var team2 = Team ("Manchester City",R.drawable.manchestercity, 250,"Premier League")



    }

}