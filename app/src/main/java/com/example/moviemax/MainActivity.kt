package com.example.moviemax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.data.api.RetrofitInstance
import com.example.moviemax.data.repository.MovieRepository
import com.example.moviemax.navigation.NavGraph
import com.example.moviemax.ui.theme.MovieMaxTheme
import com.example.moviemax.viewModel.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieMaxTheme {
                val navController = rememberNavController()

                // Use ViewModel the correct way (scoped to activity)
                val movieViewModel: MovieViewModel = viewModel {
                    MovieViewModel(MovieRepository(RetrofitInstance.apiService))
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    // ✅ Pass paddingValues to ensure correct spacing
                    NavGraph(
                        navController = navController,
                        movieViewModel = movieViewModel,
                        navigateToSearch = { /* TODO: Implement Search Screen Navigation */ },
                        navigateToProfile = { /* TODO: Implement Profile Screen Navigation */ },
                        modifier = Modifier.padding(paddingValues) // ✅ Add this line
                    )
                }

//                val apiKey = RetrofitInstance.getApiKey(applicationContext)
            }
        }
    }
}