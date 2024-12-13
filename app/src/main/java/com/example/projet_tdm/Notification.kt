package com.example.projet_tdm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AffichageNotif() {
    val hasNotifications = true // Variable indiquant s'il y a des notifications

    Box(modifier = Modifier.fillMaxSize()) {
        // Barre de titre
        NotificationHeader()

        // Notifications au milieu
        NotificationList(
            notifications = listOf(
                NotificationItem(R.drawable.check, "Order Successfully placed", "Today 10:30PM"),
                NotificationItem(R.drawable.info, "Orders are arriving soon", "Today 10:30PM"),
                NotificationItem(R.drawable.error, "Order failed", "Today 10:30PM")
            )
        )

        // Barre de navigation inférieure
        BottomNavigationBar(hasNotifications)
    }
}

@Composable
fun NotificationList(notifications: List<NotificationItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .offset(y = 200.dp), // Position au milieu de la page
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        notifications.forEach { notification ->
            NotificationCard(notification = notification)
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Utilisation correcte
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFC6E2A).copy(alpha = 0.31f)) // Couleur avec transparence
                .padding(16.dp) // Marge intérieure
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Icône
                        Image(
                            painter = painterResource(id = notification.icon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Titre de la notification
                        Text(
                            text = notification.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    // Timestamp
                    Text(
                        text = notification.timestamp,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                // Bouton de suppression
                Image(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "Supprimer",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* Action de suppression */ }
                )
            }
        }
    }
}

@Composable
fun NotificationHeader() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.orange1), // Remplacez "image_haut" par le nom de votre image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth() // Prend toute la largeur
                .offset(y = 0.dp) // Positionne en haut
        )
        // Bouton retour
        Image(
            painter = painterResource(id = R.drawable.backnoir),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .offset(x = 16.dp, y = 64.dp)
                .clickable {
                    // Action pour le retour
                }
        )

        // Texte titre
        Text(
            text = "Notifications",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF7622),
            modifier = Modifier
                .offset(x = 220.dp, y = 64.dp),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun NavigationIcon(iconRes: Int, onClick: () -> Unit = {}, tint: Color = Color.Unspecified) {
    Image(
        painter = painterResource(id = iconRes),
        contentDescription = null,
        modifier = Modifier
            .size(30.dp)
            .clickable { onClick() },
        colorFilter = ColorFilter.tint(tint)
    )
}

@Composable
fun BottomNavigationBar(hasNotifications: Boolean) {
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
            tint = if (hasNotifications) Color.Red else Color.White
        )

        // Icône Profile
        NavigationIcon(
            iconRes = R.drawable.user,
            tint = Color.White
        )
    }
}

// Modèle de données pour une notification
data class NotificationItem(
    val icon: Int, // ID de l'icône (R.drawable)
    val title: String,
    val timestamp: String
)

@Preview(showBackground = true)
@Composable
fun PreviewAffichageNotif() {
    AffichageNotif()
}