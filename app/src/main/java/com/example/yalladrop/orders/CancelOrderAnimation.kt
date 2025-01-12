package com.example.yalladrop.orders

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.yalladrop.ui.theme.CancelOrange
import kotlinx.coroutines.delay
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.yalladrop.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CancelOrderAnimation(navController: NavHostController) {
    var currentImageIndex by remember { mutableStateOf<Int>(0) }

    // List of image resources
    val images = listOf(
        R.drawable.animation_cancel1, // Replace with your drawable resources
        R.drawable.animation_cancel2,
        R.drawable.animation_cancel3,
        R.drawable.animation_cancel4
    )
    Scaffold {
            innerPadding ->  Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CancelOrange)
            .padding(start = 10.dp, end = 10.dp, bottom = 100.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            // Run the animation to display images one after the other
            LaunchedEffect(Unit) {
                for (index in images.indices) {
                    currentImageIndex = index
                    delay(1500L) // Delay for 1 second before showing the next image
                }
            }

            AnimatedContent(
                targetState = currentImageIndex,
                transitionSpec = {
                    fadeIn(animationSpec = tween(500)) with
                            fadeOut(animationSpec = tween(500))
                },
            ) { targetIndex ->
                Image(
                    painter = painterResource(id = images[targetIndex]),
                    contentDescription = "Sequential Cancel",
                    modifier = Modifier
                        .size(150.dp, 150.dp)
                        .padding(20.dp)
                )
            }
            // Display the current image

            Text(text = "¡Order Cancelled!", style = MaterialTheme.typography.titleLarge , textAlign = TextAlign.Center)
            Text(
                text = "Your order has been successfully cancelled",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter) ,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                text = "If you have any question reach directly to our customer support",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp)

            )
            Button(onClick = {

                navController.navigate("Notifications"){
                    popUpTo(0) { inclusive = true }
                }
            }, modifier = Modifier
                .size(150.dp, 35.dp)
                .border(
                    0.5.dp,
                    MaterialTheme.colorScheme.onBackground,
                    RoundedCornerShape(45)
                ) // Apply border with the same rounded shape
                .clip(RoundedCornerShape(38)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground),


                ) {
                Text(
                    text = "Continue",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
            }
        }

    }

    }

}