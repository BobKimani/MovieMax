package com.example.moviemax.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviemax.data.model.Movie
import com.example.moviemax.screens.HomeScreen
import androidx.compose.ui.Modifier
import com.example.moviemax.screens.MovieDetailScreen
import com.example.moviemax.screens.Onboarding1
import com.example.moviemax.screens.Onboarding2
import com.example.moviemax.screens.Onboarding3
import com.example.moviemax.viewModel.MovieViewModel

@Composable
fun NavGraph(
    navController: NavHostController, // Using NavHostController as the parameter
    movieViewModel: MovieViewModel= viewModel(),
    navigateToSearch: () -> Unit,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Initialize the navigation manager only once
    val navigationManager = remember { NavigationManager(navController) }

    NavHost(navController = navController, startDestination = "Onboarding1", modifier = modifier) {
        // Onboarding Screens
        composable("Onboarding1") { Onboarding1(navigationManager = navigationManager) }
        composable("Onboarding2") { Onboarding2(navigationManager = navigationManager) }
        composable("Onboarding3") { Onboarding3(navigationManager = navigationManager) }

        // Home Screen
        composable("HomeScreen") {
            HomeScreen(
                movieViewModel = movieViewModel,
                navigateToSearch = navigateToSearch,
                navigateToProfile = navigateToProfile,
                navigationManager = navigationManager
            )
        }

        // Movie Detail Screen
        composable("MovieDetail/{movieId}") { backStackEntry ->
            val movieIdString = backStackEntry.arguments?.getString("movieId")
            val movieId = movieIdString?.toIntOrNull()
            val movie = movieViewModel.getMovieById(movieId ?: -1)

            if (movie != null) {
                // Pass the movieId and navigationManager to MovieDetailScreen
                MovieDetailScreen(movie = movie, navigationManager = navigationManager)
            } else {
                // Handle invalid movieId (you can show an error or navigate back)
                println("Invalid movieId")
            }
        }
    }
}