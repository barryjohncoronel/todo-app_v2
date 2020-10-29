package com.example.todoapp.ui.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.local.note.Note
import com.example.todoapp.databinding.ItemNoteBinding

class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteItemViewHolder>(NoteDiffCallBack()) {

    var onItemClickListener: ((note: Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        return NoteItemViewHolder(
            ItemNoteBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteItemViewHolder(
        private val itemNoteBinding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(itemNoteBinding.root) {

        fun bind(note: Note) {
            itemNoteBinding.note = note

            itemNoteBinding.root.setOnClickListener {
                onItemClickListener?.invoke(note)
            }

            itemNoteBinding.executePendingBindings()
        }
    }

    internal class NoteDiffCallBack : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

}