package com.example.my_quotes.Model

import android.icu.number.IntegerWidth
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Notes (

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    var title:String ,
    var sub_title:String,
    var notes:String,
    var priority : String,
    var date : String,
    var selected : Boolean

    ) : Parcelable