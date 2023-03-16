package com.example.my_quotes.Repository

import androidx.lifecycle.LiveData
import com.example.my_quotes.Dao.NotesDao
import com.example.my_quotes.Model.Notes

class NotesRepository( val dao : NotesDao) {

    fun getallnotes(): LiveData<List<Notes>> {
        return dao.getnotes()
    }

    fun insertnotes(notes: Notes){
        return dao.insert_notes(notes)
    }

    fun deletenotes(idlist : List<Int>){
        return dao.delete_notes(idlist)
    }

    fun updatenotes(notes: Notes){
        return dao.update_notes(notes)
    }

    fun highnotes() : LiveData<List<Notes>>{
        return dao.gethighnotes()
    }

    fun mediumnotes() : LiveData<List<Notes>>{
        return dao.getmediumnotes()
    }

    fun lownotes() : LiveData<List<Notes>>{
        return dao.getlownotes()
    }

}