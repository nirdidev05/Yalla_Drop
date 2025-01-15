package com.example.yalladrop.delivery

import android.widget.Space
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.yalladrop.R
import com.example.yalladrop.ui.theme.ConfirmedYellow
import com.example.yalladrop.ui.theme.OrangeBase
import java.util.Date


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ConfirmedOrderAnimation(navController: NavHostController) {
    var currentImageIndex by remember { mutableStateOf<Int>(0) }

    // List of image resources
    val images = listOf(
        R.drawable.animation_cancel1, // Replace with your drawable resources
        R.drawable.animation_cancel2,
        R.drawable.animation_cancel3,
        R.drawable.animation_confirmed4
    )
    Scaffold {
            innerPadding ->  Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ConfirmedYellow)
            .padding(start = 10.dp, end = 10.dp, bottom = 50.dp),

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
                    delay(1000L) // Delay for 1 second before showing the next image
                }
            }

            AnimatedContent(
                targetState = currentImageIndex,
                transitionSpec = {
                    if(targetState<=2) {
                        fadeIn(animationSpec = tween(500)) with
                                fadeOut(animationSpec = tween(500))
                    }

                    else {
                        scaleIn(animationSpec = tween(1000)) with
                                scaleOut(animationSpec = tween(500))
                    }
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

            Text(text = "¡Order Confirmed!", style = MaterialTheme.typography.titleLarge , textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 10.dp))
            Text(
                text = "Your order has been placed succesfully",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(25.dp))
/*
            Text(
                text = "Delivered by Today :  ",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 25.dp)
            )
            */
            TextButton(onClick = { /*TODO*/ }, ) {
                Text(text = "Track my order" , textAlign = TextAlign.Center , style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.SemiBold, color = OrangeBase)
            }
        }

        Column(modifier = Modifier.align(Alignment.BottomCenter) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "If you have any questions, please reach out directly to our customer support",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp)

            )
            Button(onClick = {
                navController.navigate("HomePage"){

                        popUpTo(0) { inclusive = true } // Clear the back stack

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