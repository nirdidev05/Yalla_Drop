package com.example.projet_tdm

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

@Composable
fun NavigationIcon(iconRes: Int, tint: Color = Color.Unspecified) {
    Image(
        painter = painterResource(id = iconRes),
        contentDescription = null,
        modifier = Modifier
            .size(30.dp)
            .clickable { },
        colorFilter = ColorFilter.tint(tint)
    )
}

@Composable
fun BottomNavigationBar(hasNotifications1: Boolean) {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 800.dp)
    )
    Image(
        painter = painterResource(id = R.drawable.home), // Remplacez "image_bas" par le nom de votre image
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth() // Prend toute la largeur
            .offset(y = 885.dp) // Positionne en bas
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .offset(y = 825.dp), // Ajustez la position si nécessaire
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // Icône Home
        NavigationIcon(
            iconRes = R.drawable.home1,
            tint = Color.White // Assurez-vous que la couleur est visible
        )

        // Icône Shopping
        NavigationIcon(
            iconRes = R.drawable.shopping,
            tint = Color.White
        )

        // Icône Favorites
        NavigationIcon(
            iconRes = R.drawable.heart,
            tint = Color.White
        )

        // Icône Notifications
        NavigationIcon(
            iconRes = R.drawable.notification,
            tint = if (hasNotifications1) Color.Red else Color.White
        )

        // Icône Profile
        NavigationIcon(
            iconRes = R.drawable.user,
            tint = Color.White
        )
    }
}

@Composable
fun PageWithNavigationBar(
    hasNotifications: Boolean = false,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Contenu spécifique de la page
        content()

        // Barre de navigation en bas
        BottomNavigationBar(hasNotifications1 = hasNotifications)
    }
}