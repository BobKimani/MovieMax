package com.example.moviemax.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.screens.Onboarding1
import com.example.moviemax.screens.Onboarding2
import com.example.moviemax.screens.Onboarding3
import com.example.moviemax.viewModel.MovieViewModel

@Composable
fun navgraph() {
    val navController = rememberNavController()
    val navigationManager = remember { NavigationManager(navController) }

    NavHost(navController, startDestination = "Onboarding1") {
        // Onboarding Screens
        composable("Onboarding1") { Onboarding1(navigationManager = navigationManager) }
        composable("Onboarding2") { Onboarding2(navigationManager = navigationManager) }
        composable("Onboarding3") { Onboarding3(navigationManager = navigationManager) }
    }
}