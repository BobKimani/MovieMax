package com.example.moviemax

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.screens.Onboarding1
import com.example.moviemax.screens.Onboarding2
import com.example.moviemax.screens.Onboarding3
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviemax.navigation.navgraph
import com.example.moviemax.ui.theme.MovieMaxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieMaxTheme {
                navgraph()
            }
        }
    }
}


@Composable
fun MovieMaxApp() {

}



@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
   navgraph()
}