package com.example.moviemax.screens

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.model.AuthViewModel
import com.example.moviemax.nav.Screen
import com.google.firebase.auth.userProfileChangeRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: AuthViewModel) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val user by viewModel.user.collectAsStateWithLifecycle()

    val profileUpdates = userProfileChangeRequest {
        displayName = username
    }


    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.SignUp.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F1D2B))
            .padding(top = 170.dp, start = 15.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Create Account", fontSize = 30.sp, color = Color.White, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(30.dp))
        Text("Sign up with your email and password", fontSize = 22.sp, color = Color(0xFFB0B0B0))
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username, onValueChange = { username = it }, label = { Text("Username", color = Color(0xFFB0B0B0)) },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF00D8D8), unfocusedBorderColor = Color.Gray, focusedTextColor = Color.White, unfocusedTextColor = Color.White)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it }, label = { Text("Email", color = Color(0xFFB0B0B0)) },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF00D8D8), unfocusedBorderColor = Color.Gray, focusedTextColor = Color.White, unfocusedTextColor = Color.White)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = password, onValueChange = { password = it }, label = { Text("Password", color = Color(0xFFB0B0B0)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF00D8D8), unfocusedBorderColor = Color.Gray, focusedTextColor = Color.White, unfocusedTextColor = Color.White)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                if (email.isNotBlank() && username.isNotBlank() && password.isNotBlank()) {
                    viewModel.signUp(
                        email, password,username,
                        onSuccess = { navController.navigate(Screen.Home.route) },
                        onError = { e -> println("Sign-up failed: ${e.message}") },

                    )
                }
            },
            modifier = Modifier.fillMaxWidth().height(55.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00D8D8))
        ) {
            Text("Continue", fontSize = 22.sp, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(90.dp))

        Row {
            Text("Already have an account? ", fontSize = 20.sp, color = Color(0xFFB0B0B0), fontWeight = FontWeight.Bold)
            Text(
                "Sign In", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00D8D8),
                textDecoration = TextDecoration.Underline, modifier = Modifier.clickable { navController.navigate(Screen.SignIn.route) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUpScreen(rememberNavController(), viewModel = AuthViewModel())
}