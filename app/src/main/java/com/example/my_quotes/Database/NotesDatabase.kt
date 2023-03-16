package com.example.my_quotes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.my_quotes.Dao.NotesDao
import com.example.my_quotes.Model.Notes
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Notes :: class] , version = 2 , exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

     abstract fun notesdao() : NotesDao



    companion object {
        @Volatile
       private var instance : NotesDatabase ?= null


        @OptIn(InternalCoroutinesApi::class)
        fun getdatabse(context: Context) : NotesDatabase{
            val tempinstance = instance

            if(tempinstance != null){
                return tempinstance
            }

            synchronized(this){
                val notesroomdatabase = Room.databaseBuilder(context , NotesDatabase :: class.java , "notes_table")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build()
                instance = notesroomdatabase
                return instance as NotesDatabase
            }
        }



    }




}