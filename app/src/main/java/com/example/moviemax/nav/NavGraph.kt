package com.example.moviemax.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import com.example.moviemax.screens.HomeScreen
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.model.AuthViewModel
//import com.example.moviemax.screens.MovieDetailScreen
import com.example.moviemax.screens.OnboardingScreen
import com.example.moviemax.screens.SignInScreen
import com.example.moviemax.screens.SignUpScreen


sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object Home : Screen("home") // ✅ Added Home route placeholder for navigation after sign-in
    object Profile : Screen("profile")
    object Product : Screen("product")
    object Cart : Screen("cart")
}

@Composable
fun AppNavGraph(navController: NavHostController) {

    val NavController = rememberNavController()
    val authViewModel = AuthViewModel()

    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(onContinue = {
                navController.navigate(Screen.SignIn.route)
            })
        }

        composable(Screen.SignIn.route) {
            SignInScreen(navController, authViewModel)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(navController, authViewModel)
        }



//        // Home Screen
//        composable("HomeScreen") {
//            HomeScreen(
//                movieViewModel = movieViewModel,
//                navigateToSearch = navigateToSearch,
//                navigateToProfile = navigateToProfile,
//                navigationManager = navigationManager
//            )
//        }
//
//        // Movie Detail Screen
//        composable("MovieDetail/{movieId}") { backStackEntry ->
//            val movieIdString = backStackEntry.arguments?.getString("movieId")
//            val movieId = movieIdString?.toIntOrNull()
//            val movie = movieViewModel.getMovieById(movieId ?: -1)
//
//            if (movie != null) {
//                // Pass the movieId and navigationManager to MovieDetailScreen
//                MovieDetailScreen(movie = movie, navigationManager = navigationManager)
//            } else {
//                // Handle invalid movieId (you can show an error or navigate back)
//                println("Invalid movieId")
//            }
//        }
    }
}