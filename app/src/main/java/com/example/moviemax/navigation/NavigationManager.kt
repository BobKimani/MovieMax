package com.example.moviemax.navigation

import androidx.navigation.NavController

class NavigationManager(private val navController: NavController) {

    fun navigateToOnboarding2() {
        navController.navigate("Onboarding2")
    }

    fun navigateToOnboarding3() {
        navController.navigate("Onboarding3")
    }

    fun navigateToOnboarding1() {
        navController.navigate("Onboarding1")
    }
}