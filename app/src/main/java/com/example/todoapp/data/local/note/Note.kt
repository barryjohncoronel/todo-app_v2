package com.example.todoapp.data.local.note

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Entity(tableName = "note")
@Parcelize
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    var title: String,

    var message: String,

    var done: Boolean = false
) : Parcelable