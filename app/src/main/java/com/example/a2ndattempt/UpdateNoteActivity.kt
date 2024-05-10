package com.example.a2ndattempt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a2ndattempt.databinding.ActivityUpdateBinding

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db:NoteDatabaseHelper
    private  var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_update)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if (noteId == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.updatetitleEditText.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        binding.updatesaveButton.setOnClickListener {
            val newTitle = binding.updatetitleEditText.text.toString()
            val  newContent = binding.updatecontentEditText.text.toString()
            val updateNote = Note(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Changes saved",Toast.LENGTH_SHORT).show()
        }

    }
}