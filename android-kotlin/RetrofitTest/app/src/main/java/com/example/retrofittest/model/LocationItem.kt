package com.example.retrofittest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class LocationItem(
    val id : Int,
    val name : String,
    val description : String,
    val coordinates : Coordinates,
    val image : String
) : Parcelable