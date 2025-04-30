package com.example.retrofittest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class CityData(
    val city: String,
    val locations: List<LocationItem>
) : Serializable
data class CityResponse(
    val currentPage: Int,
    val totalPage: Int,
    val total: Int,
    val itemPerPage: Int,
    val pageSize: Int,
    val data: List<CityData>
) : Serializable

