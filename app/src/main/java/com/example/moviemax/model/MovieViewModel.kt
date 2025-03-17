package com.example.moviemax.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemax.api.RetrofitInstance
import com.example.moviemax.config.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import androidx.compose.runtime.State

class MovieViewModel : ViewModel() {

    private val apiService = RetrofitInstance.apiService // Use RetrofitInstance

    private val _movies = MutableStateFlow(MovieState()) // Holds UI state
    val movies: StateFlow<MovieState> = _movies

    private val _movieTrailers = mutableStateOf<List<Trailer>>(emptyList())
    val movieTrailers: State<List<Trailer>> get() = _movieTrailers

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

    fun fetchMovieTrailers(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getMovieTrailers(movieId, AppConfig.API_KEY)
                val filteredTrailers = response.results.filter { it.site == "YouTube" && it.type == "Trailer" }
                _movieTrailers.value = filteredTrailers
            } catch (e: Exception) {
                _movieTrailers.value = emptyList()
            }
        }
    }
}

// ✅ UI State class
data class MovieState(
    val data: List<Movie>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)