package com.example.notesapproomdb.database

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapproomdb.model.NotesEntity


@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note :NotesEntity)

    @Update
    suspend fun update (note: NotesEntity)

    @Delete
    suspend fun delete (note : NotesEntity)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes():LiveData<List<NotesEntity>>

    @Query("SELECT * FROM NOTES WHERE notesTitle LIKE :query OR notesDesc LIKE :query ")
    fun searchNote(query : String?):LiveData<List<NotesEntity>>
}