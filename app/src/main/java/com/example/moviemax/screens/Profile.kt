package com.example.moviemax.screens

import com.example.moviemax.nav.BottomNavigationBar
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.moviemax.R
import com.example.moviemax.nav.Screen
import com.example.moviemax.model.AuthViewModel

@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var profileHeight by remember { mutableStateOf(150.dp) }
    val animatedProfileHeight by animateDpAsState(
        targetValue = profileHeight,
        animationSpec = tween(durationMillis = 300)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141622))
            .padding(top = 55.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 80.dp), // Ensure space for BottomNavigationBar
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                val currentUser = authViewModel.user.collectAsState().value
                val userName = currentUser?.displayName ?: "Guest"
                val userEmail = currentUser?.email ?: "No email available"

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(animatedProfileHeight)
                        .background(Color(0xFF1F1D2B), shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_profile_placeholder),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(50.dp))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = userName,
                                fontSize = 26.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = userEmail, fontSize = 20.sp, color = Color.Gray)
                        }
                    }
                }
            }

            item { SectionHeader("Account") }
            items(listOf("Member" to Icons.Filled.Person, "Change Password" to Icons.Filled.Lock)) { (label, icon) ->
                ProfileOption(label, icon)
            }

            item { SectionHeader("General") }
            items(listOf("Notifications" to Icons.Default.Notifications, "Language" to Icons.Filled.Language, "Country" to Icons.Filled.Public, "Clear Cache" to Icons.Filled.Delete)) { (label, icon) ->
                ProfileOption(label, icon)
            }

            item { SectionHeader("More") }
            items(listOf("Legal and Policies" to Icons.Filled.Gavel, "Help & Feedback" to Icons.Filled.Help, "About Us" to Icons.Filled.Info)) { (label, icon) ->
                ProfileOption(label, icon)
            }

            item {
                Spacer(modifier = Modifier.height(35.dp))
                Button(
                    onClick = {
                        authViewModel.signOut {
                            navController.navigate(Screen.SignIn.route) {
                                popUpTo(Screen.Home.route) { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BCD4))
                ) {
                    Text("Log Out", color = Color.White, fontSize = 22.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        // Ensure BottomNavigationBar is visible
        BottomNavigationBar(selectedRoute = "Profile") { route ->
            navController.navigate(route)
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

@Composable
fun ProfileOption(label: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(30.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label, fontSize = 20.sp, color = Color.White)
    }
}