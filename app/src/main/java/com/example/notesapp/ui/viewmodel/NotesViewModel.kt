package com.example.notesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesapp.model.Note
import com.example.notesapp.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repository: NotesRepository
) : ViewModel() {
    fun upsert(note: Note) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(note)
    }

    fun delete(note: Note) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(note)
    }

    fun getAllNotes() = repository.getAllNotes()
}