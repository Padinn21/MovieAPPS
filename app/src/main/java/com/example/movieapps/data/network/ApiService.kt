package com.example.movieapps.data.network

import com.example.movieapps.data.model.ResponsePopularMovie
import com.example.movieapps.data.model.SerialResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/3/movie/popular?api_key=fdf48d72e336c57e099fa746a4b52b39")
    fun getPopularMovie(): Call<ResponsePopularMovie>

    @GET("/3/tv/popular?api_key=fdf48d72e336c57e099fa746a4b52b39")
    fun getTvSerial(): Call<SerialResponse>
}