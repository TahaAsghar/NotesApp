package com.example.notesapp.repository

import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.model.Note

class NotesRepository(
    private val db: NotesDatabase
) {
    suspend fun upsert(note: Note) = db.getNotesDat().upsert(note)

    suspend fun delete(note: Note) = db.getNotesDat().delete(note)

    fun getAllNotes() = db.getNotesDat().getAllNotes()
}