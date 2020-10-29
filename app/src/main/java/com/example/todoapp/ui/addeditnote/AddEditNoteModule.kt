package com.example.todoapp.ui.addeditnote

import androidx.lifecycle.ViewModel
import com.example.ct.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AddEditNoteModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddEditNoteViewModel::class)
    fun bindAddEditNoteViewModel(viewModel: AddEditNoteViewModel): ViewModel
}