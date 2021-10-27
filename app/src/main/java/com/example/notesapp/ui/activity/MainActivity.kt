package com.example.notesapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.model.Note
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.ui.AddDialogListener
import com.example.notesapp.ui.AddNoteDialog
import com.example.notesapp.ui.viewmodel.NotesViewModel
import com.example.notesapp.ui.viewmodel.NotesViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = NotesDatabase(this)
        val repository = NotesRepository(db)
        val factory = NotesViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]

        val notesAdapter = NotesAdapter(listOf(), viewModel)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = notesAdapter

        viewModel.getAllNotes().observe(this, Observer {
            notesAdapter.notes = it
            notesAdapter.notifyDataSetChanged()
        })
        binding.fabMain.setOnClickListener {
            AddNoteDialog(this, object : AddDialogListener {
                override fun onAddButtonClick(note: Note) {
                    viewModel.upsert(note)
                }
            }).show()
        }
    }
}