package com.example.googlemaps.database


import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Place::class], version = 2)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}