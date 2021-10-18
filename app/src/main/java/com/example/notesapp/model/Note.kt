package com.example.notesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_item")
data class Note(var title: String, val body: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
