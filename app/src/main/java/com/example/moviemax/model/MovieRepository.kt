package com.example.moviemax.model

import com.example.moviemax.config.AppConfig
import com.example.moviemax.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MovieRepository(private val apiService: ApiService) {

    suspend fun getMovies(): Movie {
        return withContext(Dispatchers.IO) {
            try {
                apiService.getMovies(AppConfig.API_KEY)
            } catch (e: HttpException) {
                throw Exception("Failed to load movies: ${e.message}")
            }
        }
    }

    suspend fun searchMovies(query: String): MovieResponse {
        return withContext(Dispatchers.IO) {
            try {
                apiService.searchMovies(query = query, apiKey = AppConfig.API_KEY)
            } catch (e: HttpException) {
                throw Exception("Failed to search movies: ${e.message}")
            }
        }
    }

}