package com.example.retrofittest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class Coordinates(
    val lat: Double,
    val lng: Double
) : Serializable