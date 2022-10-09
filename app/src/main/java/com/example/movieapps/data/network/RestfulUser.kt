package com.example.movieapps.data.network

import com.example.movieapps.data.model.DataUser
import com.example.movieapps.data.model.ResponseUserItem
import retrofit2.Call
import retrofit2.http.*

interface RestfulUser {

    @GET("user/")
    fun getAllUser(): Call<List<ResponseUserItem>>

    @POST("user")
    fun postUser(@Body request: DataUser): Call<ResponseUserItem>

    @PUT("user/{id}")
    fun updateUser(@Path("id") id : String, @Body request: DataUser ): Call<ResponseUserItem>

    @GET("user/{id}")
    fun getUserById(@Path("id") id : String) : Call<ResponseUserItem>
}