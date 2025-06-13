package com.example.coroutineandretrofit.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineandretrofit.R
import com.example.coroutineandretrofit.RetrofitInstance
import com.example.coroutineandretrofit.adapter.RecyclerViewAdapter
import com.example.coroutineandretrofit.databinding.ActivityMainBinding
import com.example.coroutineandretrofit.model.CryptoModel
import com.example.coroutineandretrofit.service.CryptoAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {


    private lateinit var binding: ActivityMainBinding
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null
    private lateinit var job : Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.d("app","app started")
        //RecyclerView

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        job =  CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.retrofit.getData()
            withContext (Dispatchers.Main){
                if (response.isSuccessful){
                    Log.d("API","response successful")
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                    }
                    cryptoModels?.let {
                        recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                        binding.recyclerView.adapter = recyclerViewAdapter
                    }
                }
                else{
                    Log.d("API","response failed")
                }
            }

        }

    }





    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(applicationContext,"Clicked on: ${cryptoModel.currency}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}