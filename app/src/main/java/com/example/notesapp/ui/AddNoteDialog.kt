package com.example.notesapp.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.example.notesapp.R
import com.example.notesapp.model.Note

class AddNoteDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_note)
        val save = findViewById<Button>(R.id.btnSave)
        save?.setOnClickListener {
            val title = findViewById<EditText>(R.id.tvTitleDialog)?.text.toString()
            val body = findViewById<EditText>(R.id.tvBodyDialog)?.text.toString()
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