package com.example.notesapp.ui

import com.example.notesapp.model.Note

interface AddDialogListener {
    fun onAddButtonClick (note: Note)
}