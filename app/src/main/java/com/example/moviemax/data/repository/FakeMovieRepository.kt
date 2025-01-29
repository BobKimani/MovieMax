package com.example.moviemax.data.repository

import com.example.moviemax.data.model.Movie
import com.example.moviemax.data.model.MovieResponse

class FakeMovieRepository {
    suspend fun getMovies(): MovieResponse {
        return MovieResponse(
            page = 1,
            results = listOf(
                Movie(
                    id = 1,
                    title = "Preview Movie 1",
                    overview = "A sample preview movie.",
                    poster_path = "/fakepath1.jpg",
                    release_date = "2024-01-01",
                    vote_average = 8.5F
                ),
                Movie(
                    id = 2,
                    title = "Preview Movie 2",
                    overview = "Another preview movie.",
                    poster_path = "/fakepath2.jpg",
                    release_date = "2024-01-02",
                    vote_average = 7.8F
                ),
                Movie(
                    id = 3,
                    title = "Preview Movie 3",
                    overview = "Yet another preview movie.",
                    poster_path = "/fakepath3.jpg",
                    release_date = "2024-01-03",
                    vote_average = 9.0F
                )
            ),
            total_pages = 1,
            total_results = 3
        )
    }

    suspend fun searchMovies(query: String): MovieResponse {
        return MovieResponse(
            page = 1,
            results = listOf(
                Movie(
                    id = 4,
                    title = "Searched Movie",
                    overview = "A searched preview movie result.",
                    poster_path = "/searchpath.jpg",
                    release_date = "2024-02-01",
                    vote_average = 7.5F
                )
            ),
            total_pages = 1,
            total_results = 1
        )
    }
}