package com.example.moviemax.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviemax.R

@Composable
fun Onboarding3() {
    Surface(
        color = Color(0xFF121212),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF121212))
                    .weight(3f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.picture1),
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
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
                    text = "Home of Movies!!!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "You are one click away from watching your favourite movie",
                    color = Color(0xFFB0B0B0),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

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
                    CircleIndicator(isSelected = false)
                    CircleIndicator(isSelected = true)
                }
                IconButton(
                    onClick = { /* Navigate to next screen */ },
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




    /***
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Rating Section
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star Icon",
                                tint = Color(0xFFFFD700), // Gold color for star
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "9 / 10",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }

                        // Duration Section
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.time),
                                contentDescription = "Time Icon",
                                tint = Color.Gray,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "1h 20m",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }




                    Spacer(modifier = Modifier.height(24.dp))

                    // Bottom Navigation Section
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Dots Indicator
                        Row {
                            repeat(3) { index ->
                                Box(
                                    modifier = Modifier
                                        .size(if (index == 0) 10.dp else 8.dp)
                                        .clip(CircleShape)
                                        .background(if (index == 0) Color(0xFF00DAC6) else Color.Gray)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                        }

                        // Action Button
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFF00DAC6))
                                .clickable { /* Handle button click */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Forward Arrow",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
***/

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding3() {
    Onboarding3()
}