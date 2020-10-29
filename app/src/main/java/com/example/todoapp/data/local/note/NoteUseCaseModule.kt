package com.example.todoapp.data.local.note

import dagger.Binds
import dagger.Module

@Module
interface NoteUseCaseModule {

    @Binds
    fun bindNoteLoader(noteLoaderImpl: NoteLoaderImpl): NoteLoader

    @Binds
    fun bindNoteSaver(noteSaverImpl: NoteSaverImpl): NoteSaver

    @Binds
    fun bindNoteDeleter(noteDeleterImpl: NoteDeleterImpl): NoteDeleter
}