package com.example.todoapp.data.local.note

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note): Completable

    @Update
    fun update(note: Note): Completable

    @Delete
    fun delete(note: Note): Completable

    @Query("DELETE FROM note")
    fun deleteAllNotes(): Completable

    @Query("SELECT * FROM note")
    fun getAllNotesLive(): LiveData<List<Note>>
}