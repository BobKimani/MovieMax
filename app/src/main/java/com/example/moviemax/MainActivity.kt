package com.example.moviemax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.model.AuthViewModel
import com.example.moviemax.nav.AppNavGraph
import com.example.moviemax.screens.HomeScreen
import com.example.moviemax.screens.OnboardingScreen
import com.example.moviemax.screens.ProfileScreen
import com.example.moviemax.screens.SignInScreen
import com.example.moviemax.screens.SignUpScreen
import com.example.moviemax.ui.theme.MovieMaxTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieMaxTheme {

                val navController = rememberNavController()
                AppNavGraph( navController = rememberNavController())


//                HomeScreen(navController = rememberNavController(), movieViewModel = viewModel())
            }
        }
    }
}