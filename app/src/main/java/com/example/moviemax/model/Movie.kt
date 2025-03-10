package com.example.moviemax.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Float,
    val results: List<Movie>
) {
    fun getPosterUrl(): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }

    fun getFormattedDate(): String {
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            formatter.format(parser.parse(releaseDate) ?: Date())
        } catch (e: Exception) {
            "Unknown Date"
        }
    }
}