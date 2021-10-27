package com.example.notesapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.example.notesapp.R
import com.example.notesapp.databinding.DialogAddNoteBinding
import com.example.notesapp.model.Note

class AddNoteDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    private lateinit var binding: DialogAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddNoteBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        val save = findViewById<Button>(R.id.btnSave)
        save?.setOnClickListener {
            val title = binding.tvTitleDialog.text.toString()
            val body = binding.tvBodyDialog.text.toString()
            val item = Note(title, body)
            addDialogListener.onAddButtonClick(item)
            dismiss()
        }
        val cancel = findViewById<Button>(R.id.btnCancel)
        cancel?.setOnClickListener {
            cancel()
        }
    }
}