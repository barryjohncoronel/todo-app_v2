package com.example.todoapp.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todoapp.data.local.note.Note


@BindingAdapter("noteTitle")
fun TextView.setNoteTitle(note: Note) {
    text = note.title
}

@BindingAdapter("noteMessage")
fun TextView.setNoteMessage(note: Note) {
    text = note.message
}