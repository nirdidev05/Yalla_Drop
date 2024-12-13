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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Myprofil() {
    var nameState by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumberState by remember { mutableStateOf(TextFieldValue("")) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var dateState by remember { mutableStateOf(TextFieldValue("")) }
    var emailState by remember { mutableStateOf(TextFieldValue("")) } // État pour l'email
    val hasnotif = false
    PageWithNavigationBar(hasnotif) {
        // État pour le numéro de téléphone

        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                imageUri = uri
            }

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
                        text = "My profil",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        fontSize = 32.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(contentAlignment = Alignment.TopStart) {
                    imageUri?.let { uri ->
                        val inputStream: InputStream? =
                            LocalContext.current.contentResolver.openInputStream(uri)
                        val bitmap = BitmapFactory.decodeStream(inputStream)?.asImageBitmap()
                        bitmap?.let {
                            Image(
                                bitmap = it,
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .size(170.dp)
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .offset(x = 105.dp, y = 80.dp)
                                    .clickable { launcher.launch("image/*") }
                            )
                        }
                    } ?: run {
                        Image(
                            painter = painterResource(id = R.drawable.photo1),
                            contentDescription = "Select New Image",
                            modifier = Modifier
                                .size(170.dp)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .offset(x = 105.dp, y = 80.dp)
                                .clickable { launcher.launch("image/*") }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(120.dp)) // Ajuste l'espacement après la photo

                // Champs OutlinedTextField après l'image
                OutlinedTextField(
                    value = nameState,
                    onValueChange = { newText -> nameState = newText },
                    label = {
                        Text(text = "Name", color = Color(0xFFFF7622))
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFF7622),
                        unfocusedBorderColor = Color(0xFFFF7622),
                        containerColor = Color(0xFFFC6E2A).copy(alpha = 0.31f) // Appliquer la couleur avec opacité
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = dateState,
                    onValueChange = { newText ->
                        if (newText.text.length <= 10) { // Limiter à 10 caractères pour jj/mm/aaaa
                            dateState = newText
                        }
                    },
                    label = {
                        Text(
                            text = "Date d'anniversaire",
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
                        .fillMaxWidth()
                        .height(60.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = phoneNumberState, // Utilisation de phoneNumberState pour le numéro de téléphone
                    onValueChange = { newText ->
                        // Optionnel : Ajoutez une validation ici pour n'accepter que les chiffres
                        if (newText.text.all { it.isDigit() } || newText.text.isEmpty()) {
                            phoneNumberState = newText // Mise à jour de l'état
                        }
                    },
                    label = {
                        Text(
                            text = "Phone Number", // Label pour le numéro de téléphone
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
                        .fillMaxWidth()
                        .height(60.dp), // Hauteur du champ de texte
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone // Utilisation du type de clavier pour les numéros de téléphone
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = emailState,
                    onValueChange = { newText -> emailState = newText },
                    label = {
                        Text(
                            text = "Email Id*",
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
                        .fillMaxWidth()
                        .height(60.dp) // Hauteur du champ de texte
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center // Centrer le contenu dans la Box
                ) {
                    Button(
                        onClick = { /* Action du bouton */ },
                        colors = ButtonDefaults.buttonColors(Color(0xFFFF7622)), // Couleur orange
                        shape = RoundedCornerShape(30.dp), // Coins plus arrondis
                        modifier = Modifier
                            .width(150.dp) // Largeur du bouton
                            .height(40.dp) // Hauteur du bouton
                            .offset(y = -75.dp)
                    ) {
                        Text(
                            text = "Update Profile",
                            fontSize = 14.sp,
                            color = Color.White // Texte en blanc
                        )
                    }
                }


            }


        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewMyprofil() {
    Myprofil()
}