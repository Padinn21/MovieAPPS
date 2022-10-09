package com.example.movieapps.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movieapps.data.model.ResponsePopularMovie
import com.example.movieapps.data.model.ResponsePopularMovieItem
import com.example.movieapps.data.model.SerialResponse
import com.example.movieapps.data.model.SerialResponseItem
import com.example.movieapps.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelPopularMovie: ViewModel() {

    fun callApiPopularMovie(callback: (List<ResponsePopularMovieItem>) -> Unit) {
        ApiClient.instance.getPopularMovie()
            .enqueue(object : Callback<ResponsePopularMovie> {
                override fun onResponse(
                    call: Call<ResponsePopularMovie>,
                    response: Response<ResponsePopularMovie>
                ) {
                    if (response.isSuccessful) {
                        return callback(response.body()!!.results)
                    }
                }

                override fun onFailure(call: Call<ResponsePopularMovie>, t: Throwable) {
                    Log.e("Movie", "onFailure: ${t.message}", )
                }

            })
    }

    fun callApiTvSerial(callback : (List<SerialResponseItem>)-> Unit) {
        ApiClient.instance.getTvSerial()
            .enqueue(object  : Callback<SerialResponse> {
                override fun onResponse(
                    call: Call<SerialResponse>,
                    response: Response<SerialResponse>
                ) {
                    if (response.isSuccessful) {
                        return callback(response.body()!!.results)
                    }
                }

                override fun onFailure(call: Call<SerialResponse>, t: Throwable) {
                    Log.e("TvSerial", "onFailure: ${t.message}", )

                }

            })
    }
}