package com.example.coroutineandretrofit.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineandretrofit.R
import com.example.coroutineandretrofit.adapter.RecyclerViewAdapter
import com.example.coroutineandretrofit.databinding.ActivityMainBinding
import com.example.coroutineandretrofit.model.CryptoModel
import com.example.coroutineandretrofit.service.CryptoAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {


    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //RecyclerView

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager


    }


    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(applicationContext,"Clicked on: ${cryptoModel.currency}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}