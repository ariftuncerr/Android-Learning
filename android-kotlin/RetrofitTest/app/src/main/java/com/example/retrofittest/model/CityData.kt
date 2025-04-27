package com.example.retrofittest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class CityData(
    val city: String,
    val locations: List<LocationItem>
) : Parcelable
@Parcelize
data class CityResponse(
    val currentPage: Int,
    val totalPage: Int,
    val total: Int,
    val itemPerPage: Int,
    val pageSize: Int,
    val data: List<CityData>
) : Parcelable

