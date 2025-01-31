package com.example.moviemax.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.moviemax.data.model.Movie
import com.example.moviemax.viewModel.MovieViewModel
import com.example.moviemax.navigation.NavigationManager
import com.example.moviemax.ui.components.BottomNavigationBar

@Composable
fun HomeScreen(
    movieViewModel: MovieViewModel,
    navigateToSearch: () -> Unit,
    navigateToProfile: () -> Unit,
    navigationManager: NavigationManager
) {
    var selectedRoute by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(selectedRoute = selectedRoute, onItemSelected = { route ->
                selectedRoute = route
                when (route) {
                    "search" -> navigateToSearch()
                    "profile" -> navigateToProfile()
                }
            })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedRoute) {
                "home" -> MovieListScreen(movieViewModel)
            }
        }
    }
}

@Composable
fun MovieListScreen(movieViewModel: MovieViewModel) {
    val movies by movieViewModel.movies.collectAsState()

    // Fetch movies when the composable loads
    LaunchedEffect(Unit) {
        movieViewModel.fetchMovies()
    }

    when {
        movies.isLoading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
        movies.error != null -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Failed to load movies: ${movies.error}",
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(movies.data ?: emptyList()) { movie ->
                    MovieItem(movie = movie)
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(movie.getPosterUrl()),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(end = 20.dp)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
            Text(
                text = "Vote average: ${movie.vote_average}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
            Text(
                text = "Release Date: ${movie.release_date}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
        }
    }
}