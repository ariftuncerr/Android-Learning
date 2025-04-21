package com.example.retrofittest.model

import java.io.Serializable

data class LocationItem(
    val id : Int,
    val name : String,
    val description : String,
    val coordinates : Coordinates,
    val image : String
) : Serializable