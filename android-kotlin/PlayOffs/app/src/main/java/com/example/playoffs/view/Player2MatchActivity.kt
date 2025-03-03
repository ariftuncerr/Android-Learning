package com.example.playoffs.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.playoffs.R
import com.example.playoffs.RetrofitClient
import com.example.playoffs.databinding.ActivityPlayer2MatchBinding
import com.example.playoffs.model.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Player2MatchActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlayer2MatchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayer2MatchBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        loadData()






    }
    fun loadData(){
        RetrofitClient.instance.getTeams(39, 2023).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful) {
                    val teams = response.body()?.response
                    teams?.forEach { team ->
                        Log.d("API", "Takım: ${team.team.name}, Lig: ${team.league.name}, Rating: ${team.rating}")
                    }
                }
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Log.e("API", "Hata: ${t.message}")
            }
        })
    }

}