package com.example.notesapproomdb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val notesTitle: String,
    val notesDesc: String
):Parcelable
