package com.example.retrofittest.service

import com.example.retrofittest.model.CityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CityAPI {
    @GET("page-{page}.json")
    suspend fun getCitiesByPage(@Path("page") page: Int): Response<CityResponse>


}