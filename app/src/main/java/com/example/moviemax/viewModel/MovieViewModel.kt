package com.example.moviemax.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemax.data.model.Movie
import com.example.moviemax.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableStateFlow(MovieState())
    val movies: StateFlow<MovieState> = _movies

    fun fetchMovies() {
        viewModelScope.launch {
            _movies.value = MovieState(isLoading = true)
            try {
                val response = movieRepository.getMovies()
                _movies.value = MovieState(data = response.results)
            } catch (e: Exception) {
                _movies.value = MovieState(error = e.message)
            }
        }
    }
}

data class MovieState(
    val data: List<Movie>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)