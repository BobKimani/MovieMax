package com.example.moviemax.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.moviemax.R

// Define a constant for your light blue color
private val LightBlue = Color(0xFF00D8D8)

sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val route: String
) {
    object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    object Search : BottomNavItem("Search", R.drawable.ic_search, "search")
    object Profile : BottomNavItem("Profile", R.drawable.profile, "profile")
}

@Composable
fun BottomNavigationBar(selectedRoute: String, onItemSelected: (String) -> Unit) {
    NavigationBar(containerColor = Color.Black) { // Background remains black
        val items = listOf(BottomNavItem.Home, BottomNavItem.Search, BottomNavItem.Profile)

        items.forEach { item ->
            val isSelected = selectedRoute == item.route // Check if selected

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (isSelected) Color(0xFF00D8D8) else Color.Gray // Light Blue if selected
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (isSelected) Color(0xFF00D8D8) else Color.Gray // Light Blue if selected
                    )
                },
                selected = isSelected,
                onClick = { onItemSelected(item.route) },
                alwaysShowLabel = true, // Keep labels visible
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF00D8D8), // Light Blue
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color(0xFF00D8D8), // Light Blue
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent // Removes white background
                )
            )
        }
    }
}