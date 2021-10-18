package com.example.notesapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.model.Note
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.ui.viewmodel.NotesViewModel
import com.example.notesapp.ui.viewmodel.NotesViewModelFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = NotesDatabase(this)
        val repository = NotesRepository(db)
        val factory = NotesViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]

    }
}