package com.example.notesapproomdb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesapproomdb.database.NoteDatabase
import com.example.notesapproomdb.repository.NoteRepository
import com.example.notesapproomdb.viewmodel.NoteViewModel
import com.example.notesapproomdb.viewmodel.NoteViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()





    }
    private fun setUpViewModel(){
        val noteRepository  = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application,noteRepository)
        noteViewModel =ViewModelProvider(this,viewModelProviderFactory)[NoteViewModel::class.java]


    }
}