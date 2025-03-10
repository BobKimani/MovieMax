package com.example.moviemax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.model.AuthViewModel
import com.example.moviemax.nav.AppNavGraph
import com.example.moviemax.screens.SignInScreen
import com.example.moviemax.ui.theme.MovieMaxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieMaxTheme {

//                SignInScreen(navController = rememberNavController(), viewModel = AuthViewModel())


                AppNavGraph( navController = rememberNavController())
//                val navController = rememberNavController()
//
//                // Use ViewModel the correct way (scoped to activity)
//                val movieViewModel: MovieViewModel = viewModel {
//                    MovieViewModel(MovieRepository(RetrofitInstance.apiService))
//                }
//
//                Scaffold(
//                    modifier = Modifier.fillMaxSize()
//                ) { paddingValues ->
//                    // ✅ Pass paddingValues to ensure correct spacing
//                    NavGraph(
//                        navController = navController,
//                        movieViewModel = movieViewModel,
//                        navigateToSearch = { /* TODO: Implement Search Screen Navigation */ },
//                        navigateToProfile = { /* TODO: Implement Profile Screen Navigation */ },
//                        modifier = Modifier.padding(paddingValues) // ✅ Add this line
//                    )


//                val apiKey = RetrofitInstance.getApiKey(applicationContext)
            }
        }
    }
}