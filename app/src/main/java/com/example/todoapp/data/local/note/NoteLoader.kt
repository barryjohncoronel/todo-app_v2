package com.example.todoapp.data.local.note

import androidx.lifecycle.LiveData

interface NoteLoader {

    fun getAllNotesLive(): LiveData<List<Note>>
}