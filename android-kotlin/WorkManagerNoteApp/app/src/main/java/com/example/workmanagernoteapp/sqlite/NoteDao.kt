package com.example.workmanagernoteapp.sqlite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NoteDao {
    @Query("Select * From Note")
    fun getAll() : Flowable<List<Note>>

    @Insert
    fun insert(note: Note) : Completable
}