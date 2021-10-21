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
import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.model.Note
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.ui.AddDialogListener
import com.example.notesapp.ui.AddNoteDialog
import com.example.notesapp.ui.viewmodel.NotesViewModel
import com.example.notesapp.ui.viewmodel.NotesViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = NotesDatabase(this)
        val repository = NotesRepository(db)
        val factory = NotesViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]

        val adapter = NotesAdapter(listOf(), viewModel)
        val recyclerView = findViewById<RecyclerView>(R.id.rvMain)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.getAllNotes().observe(this, Observer {
            adapter.notes = it
            adapter.notifyDataSetChanged()
//            Log.d("Check", "entry or not")
//            adapter.differ.submitList(adapter.notes)
        })
        val btn = findViewById<FloatingActionButton>(R.id.fabMain)
        btn.setOnClickListener {
            AddNoteDialog(this, object : AddDialogListener {
                override fun onAddButtonClick(note: Note) {
                    viewModel.upsert(note)
                }
            }).show()
        }
    }
}