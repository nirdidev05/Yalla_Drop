package com.example.yalladrop

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LeaveReview(navController: NavHostController) {
    PrincipaleBackGroound(title = "Leave Review" , navController) {
        var currentRating by remember { mutableStateOf(3) }
        val text = rememberSaveable { mutableStateOf("") }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Box(
                    modifier = Modifier
                        .size(157.dp, 157.dp)
                        .clip(shape = RoundedCornerShape(20.dp)),

                    contentAlignment = Alignment.Center

                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.images),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(shape = RoundedCornerShape(20.dp)),
                    )

                }

                Text(
                    text = "Checken Cury",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 30.sp,
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "We'd love to know what you think of your dish.",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                RatingBar(
                    rating = currentRating,
                    starsNum = 5,
                    onRatingChange = { newRating ->
                        currentRating = newRating
                    }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Leave us your comment!",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                CustomAreaText(text = text, isEnable = true)
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /*TODO*/ } ,modifier = Modifier
                    .size(150.dp, 35.dp)
                    .border(0.5.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(45)) // Apply border with the same rounded shape
                    .clip(RoundedCornerShape(38))

                ) {
                    Text(text = "Submit" , style = MaterialTheme.typography.labelMedium , color = Color.White)
                }
                Button(onClick = { /*TODO*/ } ,modifier = Modifier
                    .size(150.dp, 35.dp)
                    .border(0.5.dp,MaterialTheme.colorScheme.onBackground, RoundedCornerShape(45)) // Apply border with the same rounded shape
                    .clip(RoundedCornerShape(38))

                ) {
                    Text(text = "Cancel" , style = MaterialTheme.typography.labelMedium , color = Color.White)
                }
            }
        }

    }
}

@Composable
fun RatingBar(
    rating: Int = 0, // Changed to Int to only handle whole stars
    starsNum: Int = 5,
    filledStarColor: Color = MaterialTheme.colorScheme.primary,
    unfilledStarColor: Color = MaterialTheme.colorScheme.primary,
    onRatingChange: (Int) -> Unit
) {
    Row {
        for (index in 1..starsNum) {
            Icon(
                imageVector = if (index <= rating) Icons.Rounded.Star else Icons.Rounded.StarOutline,
                contentDescription = null,
                tint = if (index <= rating) filledStarColor else unfilledStarColor,
                modifier = Modifier
                    .size(50.dp)
                    .clickable { onRatingChange(if (index == rating) index - 1 else index) }
            )
        }
    }
}