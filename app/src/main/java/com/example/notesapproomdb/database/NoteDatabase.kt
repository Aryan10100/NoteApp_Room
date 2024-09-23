package com.example.notesapproomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapproomdb.model.NotesEntity


@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){
    abstract fun getNoteDAO ():NoteDAO

    companion object{
    @Volatile                                                            //changes made to one thread is visible to another thread
          private var instance : NoteDatabase? =null                      //instance variable hold the singleton instance of note Data base or null
          private val LOCK =Any()                                        //is used to synchronize the database creation process

        operator fun invoke(context: Context) = instance ?:               //invoke operator allows us to create the instance of the companion object and follows the singleton pattern ensuring that one instance is created
        synchronized(LOCK){                                               // above line checks that if the  the instance is not created then it enters to initialise synchronise block using LOCK instance
            instance ?: createDatabase(context).also{      // instance ?: is use again to check that instance  is null and call create database function
                instance = it                                             // and double check locking pattern ensure thread safety during database creation
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }

}