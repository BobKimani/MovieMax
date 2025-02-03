package com.example.moviemax.navigation

import androidx.navigation.NavController
import com.example.moviemax.data.model.Movie

class NavigationManager(private val navController: NavController) {

    fun navigateToOnboarding2() {
        navController.navigate("Onboarding2")
    }

    fun navigateToOnboarding3() {
        navController.navigate("Onboarding3")
    }

    fun navigateToHomeScreen() {
        navController.navigate("HomeScreen")
    }
    fun navigateToMovieDetailScreen(movieId: Int) {
        navController.navigate("MovieDetail/$movieId")
    }


}