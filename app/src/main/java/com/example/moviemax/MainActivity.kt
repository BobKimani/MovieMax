package com.example.moviemax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.data.api.RetrofitInstance
import com.example.moviemax.data.repository.MovieRepository
import com.example.moviemax.navigation.navgraph
import com.example.moviemax.ui.theme.MovieMaxTheme
import com.example.moviemax.viewModel.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieMaxTheme {
                val navController = rememberNavController()

                // Manually create the repository and ViewModel
                val movieRepository = MovieRepository(RetrofitInstance.apiService)
                val movieViewModel = MovieViewModel(movieRepository)

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    navgraph(
                        navController = navController,
                        movieViewModel = movieViewModel,
                        navigateToSearch = { /* TODO: Implement Search Screen Navigation */ },
                        navigateToProfile = { /* TODO: Implement Profile Screen Navigation */ }
                    )
                }
            }
        }
    }
}