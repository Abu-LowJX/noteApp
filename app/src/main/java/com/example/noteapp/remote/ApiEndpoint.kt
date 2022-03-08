package com.example.noteapp.remote

import com.example.noteapp.viewmodel.NoteModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @GET("get.php")
    fun data() : Call<NoteModel>

    @FormUrlEncoded
    @POST("create.php")
    fun create(
        @Field("note") note:String
    ):Call<ResponseBody>

    @FormUrlEncoded
    @POST("update.php")
    fun update(
        @Field("id") id:String ,
        @Field("note") note:String
    ):Call<ResponseBody>

    @FormUrlEncoded
    @POST("delete.php")
    fun delete(
        @Field("id") id:String
    ):Call<ResponseBody>
}