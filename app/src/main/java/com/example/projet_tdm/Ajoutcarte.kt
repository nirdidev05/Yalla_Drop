package com.example.projet_tdm

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.InputStream
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.border

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ajoutcrt() {
    var nameState by remember { mutableStateOf(TextFieldValue("")) }
    var dateState by  remember { mutableStateOf(TextFieldValue("")) }
    var numberState by remember { mutableStateOf(TextFieldValue("")) }
    var numberState2 by remember { mutableStateOf(TextFieldValue("")) }// État pour le numéro de téléphone
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
                    text = "Add Card",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    fontSize = 32.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.banc),
                contentDescription = " ",
                modifier = Modifier
                    .size(300.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .offset(x = 35.dp, y = 30.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Champs OutlinedTextField pour le nom
            OutlinedTextField(
                value = nameState,
                onValueChange = { newText -> nameState = newText },
                label = {
                    Text(text = "Card holder name", color = Color(0xFFFF7622))
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFF7622),
                    unfocusedBorderColor = Color(0xFFFF7622),
                    containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f)
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Champs OutlinedTextField pour l'adresse
            OutlinedTextField(
                value = numberState, // Utilisation de phoneNumberState pour le numéro de téléphone
                onValueChange = { newText ->
                    // Optionnel : Ajoutez une validation ici pour n'accepter que les chiffres
                    if (newText.text.all { it.isDigit() } || newText.text.isEmpty()) {
                       numberState = newText // Mise à jour de l'état
                    }
                },
                label = {
                    Text(
                        text = "Card Number", // Label pour le numéro de téléphone
                        color = Color(0xFFFF7622)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFF7622),
                    unfocusedBorderColor = Color(0xFFFF7622),
                    containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f)
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), // Hauteur du champ de texte
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone // Utilisation du type de clavier pour les numéros de téléphone
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(46.dp) // Espace entre les champs
            ) {
                OutlinedTextField(
                    value = dateState,
                    onValueChange = { newText ->
                        if (newText.text.length <= 10) { // Limiter à 10 caractères pour jj/mm/aaaa
                            dateState = newText
                        }
                    },
                    label = {
                        Text(
                            text = "Expiry date",
                            color = Color(0xFFFF7622)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFF7622),
                        unfocusedBorderColor = Color(0xFFFF7622),
                        containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f) // Appliquer la couleur avec opacité
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .weight(1f) // Prend la moitié de l'espace horizontal
                        .height(60.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )

                OutlinedTextField(
                    value = numberState2,
                    onValueChange = { newText ->
                        if (newText.text.all { it.isDigit() } || newText.text.isEmpty()) {
                            numberState2 = newText // Mise à jour de l'état
                        }
                    },
                    label = {
                        Text(
                            text = "CVV",
                            color = Color(0xFFFF7622)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFF7622),
                        unfocusedBorderColor = Color(0xFFFF7622),
                        containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .weight(1f) // Prend l'autre moitié de l'espace horizontal
                        .height(60.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    )
                )
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box( // Englobant le bouton pour la bordure
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp)
                        .offset(y = -55.dp)
                        .border(1.dp, Color(0xFFFF7622), RoundedCornerShape(30.dp)) // Bordure autour du bouton
                ) {
                    Button(
                        onClick = { /* Action du bouton */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f)
                        ),
                        shape = RoundedCornerShape(30.dp),
                        contentPadding = PaddingValues(0.dp), // Supprime le padding interne pour éviter l'agrandissement
                        modifier = Modifier.fillMaxSize() // Remplit la Box englobante pour correspondre à la bordure
                    ) {
                        Text(
                            text = "Save Card",
                            fontSize = 14.sp,
                            color = Color(0xFFFF7622)
                        )
                    }
                }
            }
        }

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
fun PreviewAjoutcrt() {
    Ajoutcrt()
}