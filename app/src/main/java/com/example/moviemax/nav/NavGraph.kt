package com.example.moviemax.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviemax.screens.HomeScreen
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.model.AuthViewModel
import com.example.moviemax.model.MovieViewModel
import com.example.moviemax.screens.MovieDetailScreen
import com.example.moviemax.screens.OnboardingScreen
import com.example.moviemax.screens.SignInScreen
import com.example.moviemax.screens.SignUpScreen


sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object MovieDetail : Screen("movie_detail/{movieId}")

}

@Composable
fun AppNavGraph(navController: NavHostController) {

    val NavController = rememberNavController()
    val authViewModel = AuthViewModel()
    val movieViewModel = MovieViewModel()

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

        // Home Screen
        composable(Screen.Home.route) {
            HomeScreen(navController, movieViewModel)
        }

        // Movie Detail Screen
        composable("MovieDetail/{movieId}") { backStackEntry ->
            val movieIdString = backStackEntry.arguments?.getString("movieId")
            val movieId = movieIdString?.toIntOrNull()
            val movie = movieViewModel.getMovieById(movieId ?: -1)

            if (movie != null) {
                // Pass the movieId and navigationManager to MovieDetailScreen
                MovieDetailScreen(movie = movie, navController)
            } else {
                // Handle invalid movieId (you can show an error or navigate back)
                println("Invalid movieId")
            }
        }
    }
}