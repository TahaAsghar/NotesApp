package com.example.notesapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.model.Note

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes_item")
    fun getAllNotes(): LiveData<List<Note>>

}