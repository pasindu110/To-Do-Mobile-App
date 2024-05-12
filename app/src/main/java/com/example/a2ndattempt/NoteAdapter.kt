package com.example.a2ndattempt

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndattempt.databinding.NoteItemBinding

class NoteAdapter(private var notes: List<Note>, private val context: Context) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val db: NoteDatabaseHelper = NoteDatabaseHelper(context)

    inner class NoteViewHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.titleTextView.text = note.title
            binding.contentTextView.text = note.content

            binding.updateButton.setOnClickListener {
                val intent = Intent(context, UpdateNoteActivity::class.java).apply {
                    putExtra("note_id", note.id)
                }
                context.startActivity(intent)
            }

            binding.deleteButton.setOnClickListener {
                db.deleteNote(note.id)
                refreshData(db.getAllNotes())
                Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    fun refreshData(newNote: List<Note>) {
        notes = newNote
        notifyDataSetChanged()
    }
}
