package com.example.retrofittest.model


data class CityData(
    val city: String,
    val locations: List<LocationItem>
)

data class CityResponse(
    val currentPage: Int,
    val totalPage: Int,
    val total: Int,
    val itemPerPage: Int,
    val pageSize: Int,
    val data: List<CityData>
)

