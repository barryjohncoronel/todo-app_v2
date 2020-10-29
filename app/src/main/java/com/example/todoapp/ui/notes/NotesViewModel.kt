package com.example.todoapp.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.local.note.Note
import com.example.todoapp.data.local.note.NoteDeleter
import com.example.todoapp.data.local.note.NoteLoader
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val noteLoader: NoteLoader,
    private val noteDeleter: NoteDeleter
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val deleteAllNotesDone = MutableLiveData(false)

    fun getAllNotes(): LiveData<List<Note>> {
        return noteLoader.getAllNotesLive()
    }

    fun deleteAllNotes() {
        noteDeleter
            .deleteAllNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    deleteAllNotesDone.value = true
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e.localizedMessage)
                }
            })
    }
}
