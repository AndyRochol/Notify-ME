package com.example.my_quotes.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.my_quotes.Model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM NOTES_TABLE")
    fun getnotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM NOTES_TABLE WHERE priority= 1")
    fun gethighnotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM NOTES_TABLE WHERE priority= 2")
    fun getmediumnotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM NOTES_TABLE WHERE priority= 3")
    fun getlownotes() : LiveData<List<Notes>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert_notes(notes: Notes)

    @Query("DELETE FROM NOTES_TABLE WHERE id in (:idlist)")
    fun delete_notes(idlist : List<Int>)

    @Update
    fun update_notes(notes: Notes)


}