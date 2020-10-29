package com.example.todoapp.ui.addeditnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.local.note.Note
import com.example.todoapp.data.local.note.NoteSaver
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AddEditNoteViewModel @Inject constructor(
    private val noteSaver: NoteSaver
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    var note: Note? = null
    val title = MutableLiveData("")
    val message = MutableLiveData("")
    val transactionDone = MutableLiveData(false)

    fun onSaveClicked() {
        if (note == null) {
            addNote()
        } else {
            editNote()
        }
    }

    private fun addNote() {
        noteSaver
            .addNote(
                Note(
                    id = 0,
                    title = title.value!!.trim(),
                    message = message.value!!.trim(),
                    done = false
                )
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    transactionDone.value = true
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e.localizedMessage)
                }
            })
    }

    private fun editNote() {
        note!!.title = title.value!!
        note!!.message = message.value!!

        noteSaver
            .editNote(
                note!!
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    transactionDone.value = true
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Timber.e(e.localizedMessage)
                }
            })
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

}