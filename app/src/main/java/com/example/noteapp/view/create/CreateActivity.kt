package com.example.noteapp.view.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.noteapp.databinding.ActivityCreateBinding
import com.example.noteapp.remote.ApiRetrofit
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private val api by lazy { ApiRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = "Create"
        setupListener()
    }

    private fun setupListener() {
        binding.buttonCreate.setOnClickListener {
            val message = binding.editNote.text.toString()
            if (message.isNotEmpty()) {
                Log.e("CreateActivity", message)
                api.create(message)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                Toast.makeText(applicationContext, response.body()!!.toString(), Toast.LENGTH_LONG).show()
                                finish()
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
            } else
                Toast.makeText(applicationContext, "Cannot be empty", Toast.LENGTH_LONG).show()
        }
    }
}