package com.example.moviemax.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow(MovieState()) // ✅ Store as MovieState
    val movies: StateFlow<MovieState> = _movies

    fun fetchMovies() {
        viewModelScope.launch {
            _movies.value = MovieState(isLoading = true) // ✅ Set loading state
            try {
                val response = movieRepository.getMovies()
                _movies.value = MovieState(data = response.results) // ✅ Store data
            } catch (e: Exception) {
                _movies.value = MovieState(error = e.message) // ✅ Store error
            }
        }
    }

    fun getMovieById(id: Int): Movie? {
        return _movies.value.data?.find { it.id == id }
    }
}

// ✅ Restore MovieState class
data class MovieState(
    val data: List<Movie>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)