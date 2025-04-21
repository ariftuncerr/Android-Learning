package com.example.retrofittest.repository

import com.example.retrofittest.model.CityResponse
import com.example.retrofittest.service.CityAPI
import com.example.retrofittest.service.CityClient
import retrofit2.Response

class CityRepository {
    suspend fun getCities(page: Int): Response<CityResponse> {
        return CityClient.apiService.getCitiesByPage(page)
    }
}