package com.example.moviemax.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviemax.model.AuthViewModel
import com.example.moviemax.model.Movie
import com.example.moviemax.model.MovieViewModel
import com.example.moviemax.nav.BottomNavigationBar
import com.example.moviemax.nav.Screen
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    movieViewModel: MovieViewModel,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val user by authViewModel.user.collectAsState() // Observe user state
    val movieState by movieViewModel.movies.collectAsState() // Observe movie state
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedRoute = "home",
                onItemSelected = { navController.navigate(it) }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1F1D2B))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 35.dp, start = 16.dp, end = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                // Greeting Row with Search Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Wagwan, ${user?.displayName ?: "User"}",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Let's stream your favorite movie",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
                        )
                    }

                    // Search Icon Button
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Search Icon", tint = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                SearchBar(navController)

                Spacer(modifier = Modifier.height(16.dp))

                FeaturedCarousel(movieState.data ?: emptyList(), navController)

                Spacer(modifier = Modifier.height(16.dp))



                // Movie List
                MovieListScreen(movieViewModel, navController)
            }
        }
    }
}

@Composable
fun SearchBar(navController: NavController) {
    OutlinedTextField(
        value = "",
        onValueChange = { /* No typing functionality needed */ },
        placeholder = { Text("Search for a movie") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .padding(bottom = 10.dp)
            .clickable {
                navController.navigate(Screen.Search.route)
            },
        enabled = false, // Disable text input
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color.White,
            disabledTextColor = Color.White,
            disabledPlaceholderColor = Color.Gray,
            disabledLeadingIconColor = Color.White
        )
    )
}

@Composable
fun FeaturedCarousel(
    movies: List<Movie>,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(movies) { movie ->
                Card(
                    modifier = Modifier
                        .width(160.dp)
                        .height(220.dp)
                        .clickable {
                            navController.navigate("movie_detail/${movie.id}")
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = rememberAsyncImagePainter(movie.getPosterUrl()),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(12.dp))
                        )
                        // Gradient overlay at the bottom
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black),
                                        startY = 150f
                                    )
                                )
                        )
                        // Movie title at the bottom
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = movie.title ?: "No Title",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MovieListScreen(movieViewModel: MovieViewModel, navController: NavController) {
    val movieState by movieViewModel.movies.collectAsState()

    LaunchedEffect(Unit) {
        movieViewModel.fetchMovies()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Available Movies",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 8.dp)
        )

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
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(movieState.data ?: emptyList()) { movie ->
                        MovieItem(movie = movie, onClick = {
                            navController.navigate("movie_detail/${movie.id}")
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .clickable { onClick() } // Navigate to detail screen
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF252836)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(movie.getPosterUrl()),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = movie.title ?: "Unknown Title",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}