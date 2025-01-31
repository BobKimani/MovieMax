package com.example.moviemax.data.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a single movie retrieved from the API.
 */
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val poster_path: String?, // Nullable as some movies may not have a poster
    @SerializedName("release_data") val release_date: String,
    @SerializedName("vote_average") val vote_average: Float,
    @SerializedName("results") val results: List<Movie>
){
    fun getPosterUrl(): String{
        return "https://image.tmdb.org/t/p/w500/$poster_path"

    }
}