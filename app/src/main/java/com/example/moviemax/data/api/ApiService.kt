package com.example.moviemax.data.api

import com.example.moviemax.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Get a list of popular movies
    @GET("/favorite/movies")
    suspend fun getMovies(
        @Query("13cc8fa118ba32c74382251618881347") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse

    // Search for movies by query
    @GET("/{account_id}/lists") // Correct endpoint for movie search
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("13cc8fa118ba32c74382251618881347") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse
}