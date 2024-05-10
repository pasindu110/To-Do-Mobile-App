package com.example.to_dolistapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a2ndattempt.Note
import com.example.a2ndattempt.NoteDatabaseHelper
import com.example.a2ndattempt.R
import com.example.a2ndattempt.UpdateNoteActivity

class NoteAdapter(private var notes: List<Note>,context: Context) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val db : NoteDatabaseHelper = NoteDatabaseHelper(context)

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView:  TextView = itemView.findViewById(R.id.titleTextView)
        val contextTextView:  TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton:  ImageView= itemView.findViewById(R.id.updateButton)
        val deleteButton:  ImageView= itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size



    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contextTextView.text = note.content

        holder.updateButton.setOnClickListener {
            val intent =   Intent(holder.itemView.context,UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)

        }

        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context," Todo deleted ",Toast.LENGTH_SHORT).show()
        }

    }
    fun refreshData(newNote: List<Note>){
        notes = newNote
        notifyDataSetChanged()

    }

}