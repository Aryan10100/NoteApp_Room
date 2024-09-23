package com.example.notesapproomdb.repository

import com.example.notesapproomdb.database.NoteDatabase
import com.example.notesapproomdb.model.NotesEntity

class NoteRepository(private val db: NoteDatabase) {        //Repository means where a container is stored
   suspend fun insertNote(note: NotesEntity) = db.getNoteDAO().insert(note)
    suspend fun deleteNote(note: NotesEntity) = db.getNoteDAO().delete(note)
    suspend fun updateNote(note: NotesEntity) = db.getNoteDAO().update(note)

    fun getAllNote() = db.getNoteDAO().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDAO().searchNote(query)
}