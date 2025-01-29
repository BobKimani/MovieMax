package com.example.moviemax.data.model

/**
 * Represents a single movie retrieved from the API.
 */
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?, // Nullable as some movies may not have a poster
    val release_date: String,
    val vote_average: Float
)