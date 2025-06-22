package com.example.googlemaps.database

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity
class Place (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "place_name")
    val name : String,
    @ColumnInfo(name = "place_comment")
    val comment : String,
    @ColumnInfo(name = "place_image")
    val image : ByteArray,
    @ColumnInfo(name = "place_lng")
    val lng: Double,
    @ColumnInfo(name = "place_lat")
    val lat: Double
){

}