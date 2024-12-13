package com.example.projet_tdm

import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background

@Composable
fun MessageList() {
    Column(modifier = Modifier.fillMaxWidth()) {
        MessageItem("I changed my choice")
        Spacer(modifier = Modifier.height(16.dp)) // Ajuste l'espace entre chaque item
        Divider(color = Color(0xFFFFD8C7), thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))


    }
}

@Composable
fun MessageItem(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), // Ajuste l'espacement vertical pour un meilleur alignement
        verticalAlignment = Alignment.CenterVertically
    ) {


        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = title ,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF666666)
            )
        }

        RadioButton(
            selected = false, // Change à true si cet élément est sélectionné
            onClick = { /* Action lors de la sélection */ },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFFFF7622),
                unselectedColor = Color(0xFFFF7622)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mymessage() {
    var text by remember { mutableStateOf("") }  // Ici, le type est String
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.cancel),
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
                    text = "Cancel Order ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    fontSize = 32.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(55.dp))

            // Liste des adresses (ajoutée ici)
            MessageList()
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Others",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF666666)
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .width(370.dp) // Largeur du champ
                    .height(120.dp), // Hauteur du champ
                shape = RoundedCornerShape(16.dp), // Coins arrondis
                label = { Text("Entrez du texte") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFC6E2A), // Couleur de la bordure quand c'est focalisé
                    unfocusedBorderColor = Color(0xFFE95322), // Couleur de la bordure quand ce n'est pas focalisé
                    containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f), // Fond transparent ou autre couleur si besoin
                    focusedLabelColor = Color.Gray, // Couleur du label lorsqu'il est focalisé
                    unfocusedLabelColor = Color.Gray


                )
            )

            Spacer(modifier = Modifier.height(16.dp)) // Ajuste l'espacement après la liste des adresses

            // Bouton "Add New Address"
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center // Centrer le contenu dans la Box
            ) {
                Button(
                    onClick = { /* Action du bouton */ },
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .width(150.dp) // Largeur du bouton
                        .height(40.dp) // Hauteur du bouton
                        .offset(y = -75.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7622), // Couleur de fond #FF7622
                        contentColor = Color.White // Couleur du texte en blanc
                    ),
                    border = BorderStroke(2.dp, Color(0xFFE95322)), // Bordure arrondie de couleur #E95322
                    contentPadding = PaddingValues() // Supprime les marges internes pour un alignement optimal
                ) {
                    Box(
                        contentAlignment = Alignment.Center, // Centre le contenu à l'intérieur du bouton
                        modifier = Modifier.fillMaxSize() // Remplit l'espace du bouton pour garantir le centrage
                    ) {
                        Text(
                            text = "Submit",
                            fontSize = 18.sp,
                            color = Color.White
                        ) // Couleur du texte

                    }
                }
            }
        }

        // Autres images et éléments de l'interface
        Image(
            painter = painterResource(id = R.drawable.home), // Remplacez "image_bas" par le nom de votre image
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth() // Prend toute la largeur
                .offset(y = 885.dp) // Positionne en bas
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background), // Remplacez "image_bas" par le nom de votre image
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth() // Prend toute la largeur
                    .offset(y = 800.dp) // Positionne en bas
            )
            // Image cliquable 1
            Image(
                painter = painterResource(id = R.drawable.home1), // Remplacez "image1" par le nom de votre image
                contentDescription = "Image 1",
                modifier = Modifier
                    .size(30.dp) // Taille de l'image cliquable
                    .offset(x = 35.dp, y = 825.dp) // Position relative
                    .clickable {
                        // Action pour la première image
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.shopping), // Remplacez "image1" par le nom de votre image
                contentDescription = "Image 2",
                modifier = Modifier
                    .size(30.dp) // Taille de l'image cliquable
                    .offset(x = 115.dp, y = 825.dp) // Position relative
                    .clickable {
                        // Action pour la première image
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.heart), // Remplacez "image1" par le nom de votre image
                contentDescription = "Image 3",
                modifier = Modifier
                    .size(30.dp) // Taille de l'image cliquable
                    .offset(x = 190.dp, y = 825.dp) // Position relative
                    .clickable {
                        // Action pour la première image
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.notification), // Remplacez "image1" par le nom de votre image
                contentDescription = "Image 4",
                modifier = Modifier
                    .size(30.dp) // Taille de l'image cliquable
                    .offset(x = 265.dp, y = 825.dp) // Position relative
                    .clickable {
                        // Action pour la première image
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.user), // Remplacez "image1" par le nom de votre image
                contentDescription = "Image 5",
                modifier = Modifier
                    .size(30.dp) // Taille de l'image cliquable
                    .offset(x = 350.dp, y = 825.dp) // Position relative
                    .clickable {
                        // Action pour la première image
                    }
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMymessage() {
    Mymessage()
}

