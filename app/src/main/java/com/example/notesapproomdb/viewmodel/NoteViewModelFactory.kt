package com.example.notesapproomdb.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapproomdb.repository.NoteRepository

class NoteViewModelFactory(val app :Application,private val noteRepository: NoteRepository) :ViewModelProvider.Factory {    //View model factory is used to instantiate the View Model Class
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app,noteRepository) as T
    }
}