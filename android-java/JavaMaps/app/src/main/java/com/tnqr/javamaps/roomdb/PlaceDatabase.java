package com.tnqr.javamaps.roomdb;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tnqr.javamaps.model.Place;

@Database(entities = {Place.class},version = 3)
public abstract class PlaceDatabase extends RoomDatabase {
    public abstract PlaceDao placeDao();


}
