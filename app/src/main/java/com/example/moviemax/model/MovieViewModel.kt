package com.example.moviemax.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemax.api.RetrofitInstance
import com.example.moviemax.config.AppConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MovieViewModel : ViewModel() {

    private val apiService = RetrofitInstance.apiService // Use RetrofitInstance

    private val _movies = MutableStateFlow(MovieState()) // Holds UI state
    val movies: StateFlow<MovieState> = _movies

    fun fetchMovies() {
        viewModelScope.launch {
            _movies.value = MovieState(isLoading = true) // Set loading state
            try {
                val response = apiService.getMovies(AppConfig.API_KEY)
                _movies.value = MovieState(data = response.results) // Store movie data
            } catch (e: HttpException) {
                _movies.value = MovieState(error = "Failed to load movies: ${e.message}")
            } catch (e: Exception) {
                _movies.value = MovieState(error = e.message)
            }
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            _movies.value = MovieState(isLoading = true)
            try {
                val response = apiService.searchMovies(query = query, apiKey = AppConfig.API_KEY)
                _movies.value = MovieState(data = response.results)
            } catch (e: HttpException) {
                _movies.value = MovieState(error = "Failed to search movies: ${e.message}")
            } catch (e: Exception) {
                _movies.value = MovieState(error = e.message)
            }
        }
    }

    fun getMovieById(id: Int): Movie? {
        return _movies.value.data?.find { it.id == id }
    }
}

// ✅ UI State class
data class MovieState(
    val data: List<Movie>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)