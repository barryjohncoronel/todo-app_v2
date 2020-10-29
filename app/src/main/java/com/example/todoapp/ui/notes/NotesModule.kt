package com.example.todoapp.ui.notes

import androidx.lifecycle.ViewModel
import com.example.ct.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface NotesModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    fun bindNotesViewModel(viewModel: NotesViewModel): ViewModel
}