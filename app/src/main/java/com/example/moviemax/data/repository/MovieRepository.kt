package com.example.moviemax.data.repository

import com.example.moviemax.data.api.ApiService
import com.example.moviemax.data.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MovieRepository(private val apiService: ApiService) {

    private val apiKey = "13cc8fa118ba32c74382251618881347"

    suspend fun getMovies(): MovieResponse {
        return withContext(Dispatchers.IO) {
            try {
                apiService.getMovies(apiKey)
            } catch (e: HttpException) {
                throw Exception("Failed to load movies: ${e.message}")
            }
        }
    }

    suspend fun searchMovies(query: String): MovieResponse {
        return withContext(Dispatchers.IO) {
            try {
                apiService.searchMovies(query = query, apiKey = apiKey)
            } catch (e: HttpException) {
                throw Exception("Failed to search movies: ${e.message}")
            }
        }
    }
}