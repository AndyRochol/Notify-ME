package com.example.my_quotes.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.my_quotes.Database.NotesDatabase
import com.example.my_quotes.Model.Notes
import com.example.my_quotes.Repository.NotesRepository

class Notesviewmodel(application: Application) : AndroidViewModel(application) {

    lateinit var repository : NotesRepository

    init {
        val dao = NotesDatabase.getdatabse(application).notesdao()
        repository = NotesRepository(dao)
    }

    fun addnotes(notes: Notes) {
        repository.insertnotes(notes)
    }

    fun getnotes(): LiveData<List<Notes>>{
        return repository.getallnotes()
    }

    fun deletenotes(idlist: List<Int>){
        repository.deletenotes(idlist)
    }

    fun updatenotes(notes: Notes){
        repository.updatenotes(notes)
    }

    fun highnotes() : LiveData<List<Notes>>{
        return repository.highnotes()
    }

    fun mediumnotes() : LiveData<List<Notes>>{
        return repository.mediumnotes()
    }
    fun lownotes() : LiveData<List<Notes>>{
        return repository.lownotes()
    }
}