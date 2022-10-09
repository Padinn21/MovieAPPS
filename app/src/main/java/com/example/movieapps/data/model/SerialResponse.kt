package com.example.movieapps.data.model

import com.google.gson.annotations.SerializedName

data class SerialResponse(
    @SerializedName("results")
    val results: List<SerialResponseItem>
)