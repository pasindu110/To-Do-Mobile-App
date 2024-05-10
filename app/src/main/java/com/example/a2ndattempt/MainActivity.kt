package com.example.a2ndattempt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2ndattempt.databinding.ActivityMainBinding
import com.example.to_dolistapp.NoteAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : NoteDatabaseHelper
    private lateinit var notesAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = NoteDatabaseHelper(this)
        notesAdapter = NoteAdapter(db.getAllNotes(),this)

        binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.noteRecyclerView.adapter = notesAdapter


        binding.addButton.setOnClickListener{
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }

}