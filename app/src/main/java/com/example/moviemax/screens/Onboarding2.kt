package com.example.moviemax.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviemax.R
import com.example.moviemax.navigation.NavigationManager


@Composable
fun Onboarding2(navigationManager: NavigationManager) {
    Surface(
        color = Color(0xFF121212),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Image and decorative circles
            Box(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.picture2),
                    contentDescription = "Onboarding Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Text content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Find the best movies here!!!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "In search of a button, find your best movie for you",
                    color = Color(0xFFB0B0B0),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Navigation dots and button
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.padding(start = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CircleIndicator(isSelected = false)
                    CircleIndicator(isSelected = true)
                    CircleIndicator(isSelected = false)
                }
                IconButton(
                    onClick = { navigationManager.navigateToOnboarding3()},
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(50.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.button),
                        contentDescription = "Button",

                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding2() {
    Onboarding2(navigationManager = NavigationManager(rememberNavController()))
}