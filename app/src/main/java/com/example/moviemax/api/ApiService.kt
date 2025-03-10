package com.example.moviemax.api

import com.example.moviemax.model.Movie
import com.example.moviemax.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Get a list of popular movies
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Movie

    // Search for movies by query
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse
}