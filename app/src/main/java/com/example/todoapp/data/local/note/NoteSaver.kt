package com.example.todoapp.data.local.note

import io.reactivex.Completable

interface NoteSaver {

    fun addNote(note: Note): Completable

    fun editNote(note: Note): Completable
}