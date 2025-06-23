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

    @Query("SELECT id From Note where note_savedOnFirebase = 0")
    fun getAllUnsavedNotes() : Flowable<List<Int>>

    @Insert
    fun insert(note: Note) : Completable

    @Query("Update Note Set note_savedOnFirebase = 1 Where id = :noteId")
    fun updateSavedOnFirebase(noteId: Int) : Completable
}