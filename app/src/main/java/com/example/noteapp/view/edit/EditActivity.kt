package com.example.noteapp.view.edit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.ActivityEditBinding
import com.example.noteapp.remote.ApiRetrofit
import com.example.noteapp.viewmodel.NoteModel
import com.example.noteapp.viewmodel.SubmitModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


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
                .enqueue(object : Callback<SubmitModel> {
                    override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                        if (response.isSuccessful)
                            Toast.makeText(applicationContext, response.body()!!.message, Toast.LENGTH_LONG).show()
                        finish()
                    }

                    override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                        Log.e("EditActivity", t.message.toString())
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        finish()
                    }
                })
        }
    }
}