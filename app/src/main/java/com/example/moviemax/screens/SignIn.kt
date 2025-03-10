package com.example.moviemax.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.R
import com.example.moviemax.model.AuthViewModel
import com.example.moviemax.nav.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController, viewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val user by viewModel.user.collectAsState()

    // Navigate to home screen if user is authenticated
    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate("home") {
                popUpTo("signin") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Dark background from onboarding
            .padding(top = 50.dp, start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = 100.dp))

        Text(
            "Welcome Back",
            fontSize = 30.sp,
            color = Color.White, // White text to match onboarding
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Sign in with your email or password",
            fontSize = 22.sp,
            color = Color(0xFFB0B0B0), // Light gray from onboarding
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color(0xFFB0B0B0)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF00D8D8), // Cyan accent from onboarding
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color(0xFFB0B0B0)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF00D8D8), // Cyan accent from onboarding
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = { viewModel.signIn(email, password) },
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00D8D8)) // Cyan button
        ) {
            Text(
                "Continue",
                fontSize = 22.sp,
                color = Color.Black // Black text for contrast on cyan
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "-----Or Sign In with------",
            fontSize = 20.sp,
            color = Color(0xFFB0B0B0) // Light gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.google), // Ensure you have this drawable
            contentDescription = "Sign in with Google",
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(35.dp))

        Row {
            Text(
                "Don't have an account? ",
                fontSize = 20.sp,
                color = Color(0xFFB0B0B0),
                fontWeight = FontWeight.Bold
            )
            Text(
                "Sign Up",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                color = Color(0xFF00D8D8), // Cyan for link
                modifier = Modifier.clickable { navController.navigate(Screen.SignUp.route) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    SignInScreen(rememberNavController(), viewModel = AuthViewModel())
}