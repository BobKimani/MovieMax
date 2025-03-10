package com.example.moviemax.model

//  Represents the response from the API when fetching a list of movies.

data class MovieResponse(
    val page: Int, // The current page number of the result set
    val results: List<Movie>, // The list of movies returned
    val total_pages: Int, // Total number of pages available
    val total_results: Int // Total number of results available
)