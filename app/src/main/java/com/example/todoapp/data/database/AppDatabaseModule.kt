package com.example.todoapp.data.database

import android.content.Context
import com.example.todoapp.data.local.note.NoteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }
}