package com.example.currencyapp.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.currencyapp.R
import com.example.currencyapp.model.CryptoModel
import com.example.currencyapp.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL  = "https://raw.githubusercontent.com/"
    private var cryptoModels : ArrayList<CryptoModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //https://raw.githubusercontent.com/
       // atilsamancioglu/K21-JSONDataSet/master/crypto.json

    }
    private fun loadData(){
        val retrofit =Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    //eğer response null değilse
                    response.body()?.let{
                        cryptoModels = ArrayList(it)

                        for (cryptoModel : CryptoModel in cryptoModels!!) {
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }

                    }
                }

            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}