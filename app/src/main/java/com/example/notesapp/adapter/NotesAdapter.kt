package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.model.Note
import com.example.notesapp.ui.viewmodel.NotesViewModel

class NotesAdapter(private var viewModel: NotesViewModel) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        )

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = differ.currentList[position]
//        holder.itemView.apply {
//
//        }
        holder.itemView.findViewById<TextView>(R.id.tvTitle).text = note.title
        holder.itemView.findViewById<TextView>(R.id.tvBody).text = note.body
        holder.itemView.findViewById<TextView>(R.id.tvDelete).setOnClickListener {
            viewModel.delete(note)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}