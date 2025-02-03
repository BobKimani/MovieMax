package com.example.moviemax.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviemax.data.model.Movie
import com.example.moviemax.navigation.NavigationManager
import com.example.moviemax.ui.components.BottomNavigationBar
import com.example.moviemax.viewModel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
            BottomNavigationBar(
                selectedRoute = selectedRoute,
                onItemSelected = { route ->
                    selectedRoute = route
                    when (route) {
                        "search" -> navigateToSearch()
                        "profile" -> navigateToProfile()
                    }
                }
            )
        },
        containerColor = Color(0xFF1F1D2B)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // ✅ Fix for Scaffold padding
                .background(Color(0xFF1F1D2B))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Hello, User",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Let's stream your favorite movie",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Movie List
                MovieListScreen(movieViewModel, navigationManager)
            }
        }
    }
}

@Composable
fun MovieListScreen(movieViewModel: MovieViewModel, navigationManager: NavigationManager) {
    val movieState by movieViewModel.movies.collectAsState()

    LaunchedEffect(Unit) {
        movieViewModel.fetchMovies()
    }

    when {
        movieState.isLoading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(color = Color.White)
            }
        }
        movieState.error != null -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Failed to load movies: ${movieState.error}",
                    color = Color.Red
                )
            }
        }
        movieState.data.isNullOrEmpty() -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "No movies available",
                    color = Color.Gray
                )
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(movieState.data ?: emptyList()) { movie ->  // Ensure data is not null
                    MovieItem(movie = movie, onClick = {
                        navigationManager.navigateToMovieDetailScreen(movie.id)
                    })
                }
            }
        }
    }
}


@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF252836)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(movie.getPosterUrl()),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = movie.title ?: "Unknown Title",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Year: ${movie.releaseDate ?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Text(
                    text = "Rating: ${movie.voteAverage ?: "Unknown"}",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }
    }
}