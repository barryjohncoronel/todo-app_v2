package com.example.todoapp.data.local.note

import androidx.lifecycle.LiveData
import javax.inject.Inject

class NoteLoaderImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteLoader {

    override fun getAllNotesLive(): LiveData<List<Note>> {
        return noteDao.getAllNotesLive()
    }
}