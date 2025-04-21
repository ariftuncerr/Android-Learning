package com.example.retrofittest.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CityClient {
    private val BASE_URL = "https://storage.googleapis.com/invio-com/usg-challenge/city-location/"

    val apiService : CityAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityAPI ::class.java)
    }

}