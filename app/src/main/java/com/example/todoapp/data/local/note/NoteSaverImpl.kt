package com.example.todoapp.data.local.note

import io.reactivex.Completable
import javax.inject.Inject

class NoteSaverImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteSaver {

    override fun addNote(note: Note): Completable {
        return noteDao.insert(note)
    }

    override fun editNote(note: Note): Completable {
        return noteDao.update(note)
    }
}