package com.example.moviemax.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviemax.screens.HomeScreen
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviemax.model.AuthViewModel
import com.example.moviemax.model.MovieViewModel
import com.example.moviemax.screens.MovieDetailScreen
import com.example.moviemax.screens.OnboardingScreen
import com.example.moviemax.screens.ProfileScreen
import com.example.moviemax.screens.SearchScreen
import com.example.moviemax.screens.SignInScreen
import com.example.moviemax.screens.SignUpScreen
import com.google.firebase.auth.FirebaseAuth


sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object MovieDetail : Screen("movie_detail/{movieId}")
    object Search : Screen("search")

}

@Composable
fun AppNavGraph(navController: NavHostController) {

    val authViewModel = AuthViewModel()
    val movieViewModel = MovieViewModel()
    val auth = FirebaseAuth.getInstance()


    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController, onContinue = {
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
            HomeScreen(navController, movieViewModel,authViewModel)
        }

        // Movie Detail Screen
        composable(
            route = "movie_detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: -1 // Use getInt() instead of getString()
            val movie = movieViewModel.getMovieById(movieId)

            if (movie != null) {
                // Pass the movieId to MovieDetailScreen
                MovieDetailScreen(movie = movie, navController, movieViewModel)
            } else {
                // Handle invalid movieId (you can show an error or navigate back)
                println("Invalid movieId")
            }
        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController, authViewModel)
        }

        composable(Screen.Search.route){
            SearchScreen(navController, movieViewModel)
        }
    }
}