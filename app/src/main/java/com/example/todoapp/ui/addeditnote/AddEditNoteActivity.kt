package com.example.todoapp.ui.addeditnote

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.todoapp.R
import com.example.todoapp.data.local.note.Note
import com.example.todoapp.data.local.note.NoteLoader
import com.example.todoapp.databinding.ActivityAddEditNoteBinding
import com.example.todoapp.util.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AddEditNoteActivity : DaggerAppCompatActivity() {

    lateinit var dataBinding: ActivityAddEditNoteBinding

    @Inject
    lateinit var noteLoader: NoteLoader

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel: AddEditNoteViewModel by viewModels {
        factory
    }

    companion object {
        private const val EXTRA_NOTE = "EXTRA_NOTE"

        fun addIntent(context: Context): Intent {
            return Intent(context, AddEditNoteActivity::class.java)
        }

        fun editIntent(context: Context, note: Note): Intent {
            return Intent(context, AddEditNoteActivity::class.java).also {
                it.putExtra(EXTRA_NOTE, note)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_edit_note)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this

        // assign note if not null to let viewModel know that action is in edit mode
        intent.getParcelableExtra<Note>(EXTRA_NOTE)?.let { note ->
            viewModel.note = note

            viewModel.title.value = note.title
            viewModel.message.value  = note.message
        }

        viewModel.transactionDone.observe(this,
            Observer { transactionDone ->
                if (transactionDone) {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            })
    }
}