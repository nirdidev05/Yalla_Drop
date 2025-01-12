package com.example.yalladrop.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yalladrop.R
import com.example.yalladrop.ui.theme.Gradient
import com.example.yalladrop.ui.theme.Orangegradient
import com.example.yalladrop.ui.theme.leagueSpartan

@Composable
fun FistPage(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()){

        Image(
            painter = painterResource(id = R.drawable.firstpage), // Replace with your image resource
            contentDescription = "Image with Gradient",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = 0.8f // Adjust transparency of the gradient
                }
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Orangegradient,
                            Gradient // Change to your desired gradient color
                        )
                    )
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center).padding(bottom = 30.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.foodeck), // Replace with your image resource
                contentDescription = "Image with Gradient",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(125.dp, 125.dp)
                    .padding(bottom = 20.dp)
            )
            Text(text = "YallaDrop",
                textAlign = TextAlign.Center ,
                modifier = Modifier.padding(bottom = 10.dp),
                style = TextStyle(
                    fontFamily = leagueSpartan,
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.5.sp,
                    color = Color.Black
                ),
                color = Color.White
            )
            Text(text = "A full belly wherever you are" ,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                fontSize = 18.sp,
                color = Color.White
            )


        } 
        
        Button(onClick = {
            navController.navigate("LoginPge") {
                popUpTo(0) { inclusive = true } // Clear the entire back stack
            }
        } ,

                modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 55.dp).fillMaxWidth().height(62.dp).align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(12.dp)
            ) {
            
            Text(text = "Get Started", textAlign= TextAlign.Center, style = MaterialTheme.typography.displayMedium , color = Color.White , fontSize = 17.sp)
            
        }
        
        
    }
}
