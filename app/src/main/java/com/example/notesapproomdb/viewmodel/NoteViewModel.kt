package com.example.notesapproomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapproomdb.model.NotesEntity
import com.example.notesapproomdb.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app :Application , private  val noteRepository: NoteRepository) :AndroidViewModel(app) {
    fun addNote (note :NotesEntity) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    fun deleteNote (note :NotesEntity) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    fun updateNote (note :NotesEntity) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }

    fun getAllNote() = noteRepository.getAllNote()

    fun searchNote(query :String?) = noteRepository.searchNote(query)
}