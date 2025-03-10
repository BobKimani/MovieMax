package com.example.moviemax.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviemax.R

sealed class BottomNavItem(
    val title: String,
    val icon: Int,
    val route: String
) {
    object Home : BottomNavItem("Home", R.drawable.ic_home, "home")
    object Search : BottomNavItem("Search", R.drawable.ic_search, "search")
    object Profile : BottomNavItem("Profile", R.drawable.ic_profile, "profile")
}

@Composable
fun BottomNavigationBar(selectedRoute: String, onItemSelected: (String) -> Unit) {
    NavigationBar(containerColor = Color.Black, contentColor = Color.White) {
        val items = listOf(BottomNavItem.Home, BottomNavItem.Search, BottomNavItem.Profile)

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = selectedRoute == item.route,
                onClick = { onItemSelected(item.route) }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(selectedRoute = "home") {}
}