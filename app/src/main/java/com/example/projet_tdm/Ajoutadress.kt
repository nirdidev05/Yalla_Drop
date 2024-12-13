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
fun Ajoutadr() {
    var nameState by remember { mutableStateOf(TextFieldValue("")) }
    var adressState by remember { mutableStateOf(TextFieldValue("")) } // État pour l'adresse
    val hasnotif = false
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
                        text = "Add New Address",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        fontSize = 32.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.home3),
                    contentDescription = " ",
                    modifier = Modifier
                        .size(130.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .offset(x = 120.dp, y = 80.dp)
                )

                Spacer(modifier = Modifier.height(120.dp))

                // Champs OutlinedTextField pour le nom
                OutlinedTextField(
                    value = nameState,
                    onValueChange = { newText -> nameState = newText },
                    label = {
                        Text(text = "Name", color = Color(0xFFFF7622))
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
                    value = adressState,
                    onValueChange = { newText -> adressState = newText },
                    label = {
                        Text(
                            text = "Address",
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
                        .height(60.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Box( // Englobant le bouton pour la bordure
                        modifier = Modifier
                            .width(150.dp)
                            .height(40.dp)
                            .offset(y = -75.dp)
                            .border(
                                1.dp,
                                Color(0xFFFF7622),
                                RoundedCornerShape(30.dp)
                            ) // Bordure autour du bouton
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
                                text = "Add New Address",
                                fontSize = 14.sp,
                                color = Color(0xFFFF7622)
                            )
                        }
                    }
                }
            }


        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewAjoutadr() {
    Ajoutadr()
}