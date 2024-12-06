package com.tnqr.javamaps.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Place {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="name")
    public String name;


    @ColumnInfo(name = "longitude")
    public double longitude;


    @ColumnInfo(name = "latitude")
    public double latitude;

    public Place(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
