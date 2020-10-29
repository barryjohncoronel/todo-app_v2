package com.example.todoapp.ui.notes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.todoapp.R
import com.example.todoapp.data.local.note.Note
import com.example.todoapp.databinding.ActivityNotesBinding
import com.example.todoapp.ui.addeditnote.AddEditNoteActivity
import com.example.todoapp.ui.notes.adapter.NoteAdapter
import com.example.todoapp.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NotesActivity : DaggerAppCompatActivity() {

    lateinit var dataBinding: ActivityNotesBinding

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel: NotesViewModel by viewModels {
        factory
    }

    companion object {
        const val REQUEST_CODE_ADD = 101
        const val REQUEST_CODE_EDIT = 100
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notes_menu, menu)

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_notes)
        dataBinding.lifecycleOwner = this

        dataBinding.btnAdd.setOnClickListener {
            moveToAddNoteActivity()
        }

        val adapter = NoteAdapter()
        adapter.onItemClickListener = { note ->
            moveToEditActivity(note)
        }

        dataBinding.rvNotes.adapter = adapter

        viewModel
            .getAllNotes()
            .observe(this, Observer {
                adapter.submitList(it)
            })

        viewModel
            .deleteAllNotesDone
            .observe(this, Observer { deletionDone ->
                if (deletionDone) {
                    Toast.makeText(this, getString(R.string.all_notes_deleted), Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun moveToEditActivity(note: Note) {
        startActivityForResult(
            AddEditNoteActivity.editIntent(this, note),
            REQUEST_CODE_EDIT
        )
    }

    private fun moveToAddNoteActivity() {
        startActivityForResult(
            AddEditNoteActivity.addIntent(this),
            REQUEST_CODE_ADD
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_ADD -> {
                    Toast.makeText(this, getString(R.string.noted_added), Toast.LENGTH_SHORT).show()
                }
                REQUEST_CODE_EDIT -> {
                    Toast.makeText(this, getString(R.string.note_updated), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else {
            Toast.makeText(this, getString(R.string.note_not_saved), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_notes -> {
                viewModel.deleteAllNotes()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
