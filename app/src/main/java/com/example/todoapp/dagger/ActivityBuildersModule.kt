package com.example.todoapp.dagger

import com.example.todoapp.ui.addeditnote.AddEditNoteActivity
import com.example.todoapp.ui.addeditnote.AddEditNoteModule
import com.example.todoapp.ui.notes.NotesActivity
import com.example.todoapp.ui.notes.NotesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [NotesModule::class])
    abstract fun contributeNotesActivity(): NotesActivity

    @ContributesAndroidInjector(modules = [AddEditNoteModule::class])
    abstract fun contributeAddEditNoteActivity(): AddEditNoteActivity
}