package com.example.noteapp.view.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.remote.ApiRetrofit
import com.example.noteapp.view.create.CreateActivity
import com.example.noteapp.view.edit.EditActivity
import com.example.noteapp.viewmodel.NoteModel
import com.example.noteapp.viewmodel.SubmitModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endpoint }

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupList()
        init()
    }

    private fun init() {
        binding.fabCreate.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        getNote()
    }

    private fun setupList() {
        noteAdapter = NoteAdapter(arrayListOf(), object : NoteAdapter.OnAdapterListener {
            override fun onUpdate(note: NoteModel.Data) {
                startActivity(
                    Intent(this@ListActivity, EditActivity::class.java)
                        .putExtra("note", note)
                )
            }

            override fun onDelete(note: NoteModel.Data) {
                api.delete(note.id!!)
                    .enqueue(object : Callback<SubmitModel> {
                        override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                            if (response.isSuccessful)
                                Toast.makeText(applicationContext, response.body()!!.message, Toast.LENGTH_LONG).show()
                            getNote()
                        }

                        override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                            Log.e("ListActivity", t.message.toString())
                            getNote()
                        }
                    })
            }
        })
        binding.listNote.adapter = noteAdapter
    }

    private fun getNote() {
        api.data().enqueue(object : Callback<NoteModel> {
            override fun onResponse(call: Call<NoteModel>, response: Response<NoteModel>) {
                if (response.isSuccessful) {
                    val listData = response.body()!!.notes
                    noteAdapter.setData(listData)
                    listData.forEach {
                        Log.e("MainActivity", "note ${it.note}")
                    }
                }
            }

            override fun onFailure(call: Call<NoteModel>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }
        })
    }
}