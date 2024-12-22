package com.example.projet_tdm

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpPagecentre() {
    val hasnotif = false
    val context = LocalContext.current // Obtenez le contexte local

    PageWithNavigationBar(hasnotif) {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.orange),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .offset(y = 40.dp)
            ) {
                // Ligne du titre et icône retour
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                // Action pour le retour
                            }
                    )

                    Text(
                        text = "Help Center",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        fontSize = 32.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                // Ajout des deux boutons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            // Action pour le bouton FAQ
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = "FAQ",
                            fontSize = 16.sp,
                            color = Color(0xFFFC6E2A)
                        )
                    }

                    Button(
                        onClick = {
                            // Action pour le bouton Contact Us
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:0588997700")
                            }
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF7622)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = "Contact Us",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Divider(color = Color(0xFFFFD8C7), thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

                // Section Customer Service
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Ajuste l'espacement vertical pour un meilleur alignement
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.casque), // Remplace par ton icône maison
                        contentDescription = "Home Icon",
                        tint = Color(0xFFFF7622),
                        modifier = Modifier
                            .size(50.dp) // Taille de l'icône
                            .padding(end = 16.dp)
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Customer service",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.down),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                // Lancement de l'intent pour l'appel
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:0588997700")
                                }
                                context.startActivity(intent)
                            }
                    )
                }
                Divider(color = Color(0xFFFFD8C7), thickness = 1.dp)

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHelpPagecentre() {
    HelpPagecentre()
}