package com.example.projet_tdm


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeP() {
// Déclarez les variables d'état pour chaque champ
    var phoneNumberState1 by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumberState2 by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumberState3 by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumberState4 by remember { mutableStateOf(TextFieldValue("")) }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
                .padding(horizontal = 60.dp)
                .align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Assurez-vous que la Box prend toute la largeur
            ) {
                Image(
                    painter = painterResource(id = R.drawable.spacer),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(), // Assurez-vous que l'image prend toute la largeur
                    contentScale = ContentScale.FillWidth // Assurez-vous que l'image s'étend à la largeur
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Confirm the code we sent you",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .wrapContentWidth() // Prend uniquement la largeur nécessaire au Row
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacement uniforme entre les champs
            ) {
                // Champ 1
                OutlinedTextField(
                    value = phoneNumberState1,
                    onValueChange = { newText ->
                        if (newText.text.all { it.isDigit() } && newText.text.length <= 1) {
                            phoneNumberState1 = newText
                        }
                    },
                    label = {
                        Text(text = "01", color = Color(0xFFFF7622))
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFF7622),
                        unfocusedBorderColor = Color(0xFFFF7622),
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.size(60.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                // Champ 2
                OutlinedTextField(
                    value = phoneNumberState2,
                    onValueChange = { newText ->
                        if (newText.text.all { it.isDigit() } && newText.text.length <= 1) {
                            phoneNumberState2 = newText
                        }
                    },
                    label = {
                        Text(text = "02", color = Color(0xFFFF7622))
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFF7622),
                        unfocusedBorderColor = Color(0xFFFF7622),
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.size(60.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                // Champ 3
                OutlinedTextField(
                    value = phoneNumberState3,
                    onValueChange = { newText ->
                        if (newText.text.all { it.isDigit() } && newText.text.length <= 1) {
                            phoneNumberState3 = newText
                        }
                    },
                    label = {
                        Text(text = "03", color = Color(0xFFFF7622))
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFF7622),
                        unfocusedBorderColor = Color(0xFFFF7622),
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.size(60.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                // Champ 4
                OutlinedTextField(
                    value = phoneNumberState4,
                    onValueChange = { newText ->
                        if (newText.text.all { it.isDigit() } && newText.text.length <= 1) {
                            phoneNumberState4 = newText
                        }
                    },
                    label = {
                        Text(text = "04", color = Color(0xFFFF7622))
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFFF7622),
                        unfocusedBorderColor = Color(0xFFFF7622),
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier.size(60.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )


            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Resend",
                color = Color(0xFFFF7622),
                modifier = Modifier
                    .clickable { /* Ne fait rien */ }
                    .padding(start = 16.dp, top = 8.dp) // Décalage vers la droite et espace au-dessus
            )


            Spacer(modifier = Modifier.height(60.dp))

            // Bouton "Login" avec la même largeur que les champs de texte
            Button(
                onClick = { /* Action du bouton */ },
                colors = ButtonDefaults.buttonColors(Color(0xFFFF7622)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth() // Assurez-vous que le bouton prend toute la largeur
                    .height(60.dp) // Hauteur du bouton
            ) {
                Text(
                    text = "Confirm",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .offset(y = 40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fleche),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        // Ne rien faire ici
                    }
            )
            Spacer(modifier = Modifier.width(22.dp))
            Text(
                text = "Create an account",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
        }

        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 0.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 885.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewCodeP() {
    CodeP()
}