package com.example.todoapp.data.local.note

import io.reactivex.Completable
import javax.inject.Inject

class NoteDeleterImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteDeleter {

    override fun deleteNote(note: Note): Completable {
        return noteDao.delete(note)
    }

    override fun deleteAllNotes(): Completable {
        return noteDao.deleteAllNotes()
    }
}