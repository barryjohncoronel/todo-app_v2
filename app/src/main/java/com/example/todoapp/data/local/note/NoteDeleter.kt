package com.example.todoapp.data.local.note

import io.reactivex.Completable

interface NoteDeleter {

    fun deleteNote(note: Note): Completable

    fun deleteAllNotes(): Completable
}