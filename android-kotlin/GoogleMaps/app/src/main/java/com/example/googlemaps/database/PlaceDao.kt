package com.example.googlemaps.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface PlaceDao{
    @Query("SELECT * FROM place")
    fun getAll() : Flowable <List<Place>>

    @Insert
    fun insert(place : Place) : Completable

    @Delete
    fun delete(place: Place) : Completable

}
