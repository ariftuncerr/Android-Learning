package com.example.workmanagernoteapp.sqlite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "note_title")
    val title : String,
    @ColumnInfo(name = "note_description")
    val description : String,
    @ColumnInfo(name = "note_savedOnFirebase")
    var isSavedOnFirebase: Boolean

)