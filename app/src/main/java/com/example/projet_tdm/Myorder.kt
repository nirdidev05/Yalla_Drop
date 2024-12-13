package com.example.projet_tdm

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.border
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text



@Composable
fun OrderScreen(
    itemName: String,
    itemPrice: Double,
    itemDate: String,
    itemImage: Int,
    itemsCount: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
           // .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        // Affichage de la commande
        OrderCard(name = itemName, price = itemPrice, date = itemDate, image = itemImage, count = itemsCount)
    }
}

@Composable
fun OrderCard(
    name: String,
    price: Double,
    date: String,
    image: Int,
    count: Int,
    imageOffsetX: Dp = -30.dp // Permet d'ajuster la position horizontale de l'image
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent), // Transparence complète
        elevation = CardDefaults.cardElevation(0.dp) // Supprime l'ombre
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp) // Réduit l'espace global
        ) {
            // Image à gauche, avec un décalage ajustable
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .offset(x = imageOffsetX)
            )

            Spacer(modifier = Modifier.width(0.dp)) // Réduit l'espace entre l'image et la colonne

            // Texte et boutons regroupés dans une colonne
            Column(
                modifier = Modifier
                   // .weight(1f) // Prend l'espace restant
                  //  .padding(4.dp) // Réduit le padding interne
            ) {
                // Détails de la commande (texte)
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                 //   maxLines = 1 // Limite à une ligne pour le nom
                )
                Text(
                    text = "$${price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFFE95322)
                )
                Text(
                    text = date,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = count.toString()+ " Items",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(12.dp)) // Réduit l'espacement entre le texte et les boutons

                // Boutons sous le texte
                Row(
                    //modifier = Modifier.fillMaxWidth(),
                //    horizontalArrangement = Arrangement.SpaceBetween // Espacement entre les boutons
                ) {
                    Button(
                        onClick = { /* Handle Cancel Order */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE95322)),
                        modifier = Modifier
                            .height(36.dp)
                            .width(100.dp) // Définit une largeur fixe pour réduire la longueur
                    ) {
                        Text("Cancel", color = Color.White, fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    Button(
                        onClick = { /* Handle Track Driver */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE95322).copy(alpha = 0.6f)
                        ),
                        modifier = Modifier
                            .height(36.dp)
                            .width(90.dp) // Définit une largeur fixe pour réduire la longueur
                    ) {
                        Text("Track", color = Color(0xFFE95322), fontSize = 14.sp)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Order() {

    Box(modifier = Modifier.fillMaxSize()) {
        // Image d'arrière-plan
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
                    text = "My Orders",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    fontSize = 32.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

            }

            Spacer(modifier = Modifier.height(110.dp))
            // Affichage de la commande
            OrderScreen(
                itemName = "Strawberry Shake",
                itemPrice = 20.00,
                itemDate = "29 Nov, 01:20 PM",
                itemImage = R.drawable.strawberry_shake, // Remplacez par l'image appropriée
                itemsCount = 2
            )

        }




        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.offset(y = (-270).dp), // Ajustez cette valeur pour positionner les boutons verticalement
                horizontalArrangement = Arrangement.spacedBy(16.dp) // Espace entre les boutons
            ) {
                // Premier bouton : Active
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp)
                        .border(1.dp, Color(0xFFFF7622), RoundedCornerShape(30.dp))
                ) {
                    Button(
                        onClick = { /* Action pour Active */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF7622)
                        ),
                        shape = RoundedCornerShape(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Active",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }

                // Deuxième bouton : Completed
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp)
                        .border(1.dp, Color(0xFFFF7622), RoundedCornerShape(30.dp))
                ) {
                    Button(
                        onClick = { /* Action pour Completed */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f)
                        ),
                        shape = RoundedCornerShape(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Completed",
                            fontSize = 14.sp,
                            color = Color(0xFFFF7622)
                        )
                    }
                }

                // Troisième bouton : Cancelled
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp)
                        .border(1.dp, Color(0xFFFF7622), RoundedCornerShape(30.dp))
                ) {
                    Button(
                        onClick = { /* Action pour Cancelled */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f)
                        ),
                        shape = RoundedCornerShape(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Cancelled",
                            fontSize = 14.sp,
                            color = Color(0xFFFF7622)
                        )
                    }
                }

            }

        }
        // Pied de page avec les images cliquables
        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 885.dp)
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 800.dp)
            )

            // Images cliquables avec offsets
            Image(
                painter = painterResource(id = R.drawable.home1),
                contentDescription = "Image 1",
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 35.dp, y = 825.dp)
                    .clickable { /* Action pour la première image */ }
            )
            Image(
                painter = painterResource(id = R.drawable.shopping),
                contentDescription = "Image 2",
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 115.dp, y = 825.dp)
                    .clickable { /* Action pour la deuxième image */ }
            )
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "Image 3",
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 190.dp, y = 825.dp)
                    .clickable { /* Action pour la troisième image */ }
            )
            Image(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "Image 4",
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 265.dp, y = 825.dp)
                    .clickable { /* Action pour la quatrième image */ }
            )
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Image 5",
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 350.dp, y = 825.dp)
                    .clickable { /* Action pour la cinquième image */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrder() {
    Order()
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderScreen() {
    OrderScreen(
        itemName = "Strawberry Shake",
        itemPrice = 20.00,
        itemDate = "29 Nov, 01:20 PM",
        itemImage = R.drawable.strawberry_shake, // Vérifie que l'image existe dans drawable
        itemsCount = 2
    )
}

