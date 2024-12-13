package com.example.projet_tdm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.logo), // Remplacez "logo" par le nom de votre logo dans drawable
            contentDescription = "Logo",
            modifier = Modifier
                .size(124.dp) // Définit la largeur et hauteur de 124.dp
                .offset(x = 140.dp, y = 156.dp) // Positionne le logo à (125, 156)
        )
        Image(
            painter = painterResource(id = R.drawable.home), // Remplacez "image_haut" par le nom de votre image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth() // Prend toute la largeur
                .offset(y = 0.dp) // Positionne en haut
        )
        Image(
            painter = painterResource(id = R.drawable.home), // Remplacez "image_bas" par le nom de votre image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth() // Prend toute la largeur
                .offset(y = 885.dp) // Positionne en bas
        )


        Text(
            text = "A full belly wherever you are",
            color = Color.White,
            fontSize = 17.sp, // équivalent à 17px
            lineHeight = 22.sp, // équivalent à 22px
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .offset(x = 15.dp, y = 387.dp) // Utilise offset pour positionner le texte à (106, 314)
                .align(Alignment.TopCenter), // Aligne le texte en haut du Box
            textAlign = TextAlign.Center
        )

        Text(
            text = "YallaDrop",
            color = Color.White,
            fontSize = 32.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 7.dp, y = 314.dp) // Utilise offset pour positionner le texte à (106, 314)
                .align(Alignment.TopCenter), // Aligne le texte en haut du Box
            textAlign = TextAlign.Center
        )


    }
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { /* Action du bouton */ },
            colors = ButtonDefaults.buttonColors(Color(0xFFFF7622)), // Couleur orange
            shape = RoundedCornerShape(12.dp), // Coins arrondis avec un rayon de 12.dp
            modifier = Modifier
                .width(370.dp) // Largeur du bouton
                .height(62.dp) // Hauteur du bouton
                .offset(x = 21.dp, y = 750.dp) // Position du bouton
        ) {
            Text(
                text = "Get Started",
                fontSize = 16.sp,
                color = Color.White // Texte en blanc
            )
        }
    }
}