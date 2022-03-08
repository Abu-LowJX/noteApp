package com.example.noteapp.view.edit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityEditBinding
import com.example.noteapp.remote.ApiRetrofit
import com.example.noteapp.viewmodel.NoteModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private val api by lazy { ApiRetrofit().endpoint }
    private val note by lazy { intent.getSerializableExtra("note") as NoteModel.Data }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = "Edit"
        setupListener()
        binding.updateNote.setText(note.note)
    }

    private fun setupListener() {
        binding.buttonUpdate.setOnClickListener {
            val message = binding.updateNote.text.toString()
            api.update(note.id!!, message)
                .enqueue(object : Callback<ResponseBody> {

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        Log.e("EditActivity", response.isSuccessful.toString())
                        if (response.isSuccessful) {
                            finish()
                            Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("1", t.localizedMessage!! + call.toString())
                        Log.e("2", t.printStackTrace().toString())
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        //finish()
                    }
                })
        }
    }
}