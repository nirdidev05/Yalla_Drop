package com.example.yalladrop

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.yalladrop.ui.theme.leagueSpartan

@Composable
fun LoginPge(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.loginpageimg), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { /*TODO*/ },

                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 20.dp)
                    .fillMaxWidth()
                    .height(62.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(("#DE4285F4").toColorInt())),
                shape = RoundedCornerShape(16.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google), // Replace with your image resource
                        contentDescription = null,
                        modifier = Modifier.size(27.dp ,27.dp).padding(end = 10.dp),
                    )

                    Text(
                        text = "Login via Google",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Button(onClick = {
                navController.navigate("LoginViaAcccount")

            },

                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
                    .height(62.dp)
                    .border(2.dp,  Color("#8E8E93".toColorInt()), RoundedCornerShape(25)) // Apply border with the same rounded shape
                    .clip(RoundedCornerShape(20)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp)

            ) {

                Text(
                    text = "Create an account",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium,
                    color = Color("#8E8E93".toColorInt()),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

            }

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "By signing up, you are agreeing to our",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                fontSize = 15.sp,
                color = Color("#8E8E93".toColorInt()),
            )
            Text(
                text = "Terms & Conditions",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = leagueSpartan,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    lineHeight = 25.sp,
                    letterSpacing = 0.5.sp,
                    color = Color("#FF7622".toColorInt()),
                    textDecoration = TextDecoration.Underline,

                    ),
                modifier = Modifier.padding(bottom = 50.dp).clickable {
                    // Handle the click event
                    println("Text clicked!")
                },

            )
        }
    }
}