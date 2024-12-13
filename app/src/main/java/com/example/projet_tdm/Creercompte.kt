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
fun Creer() {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var nameState by remember { mutableStateOf(TextFieldValue("")) } // État pour le nom
    var phoneNumberState by remember { mutableStateOf(TextFieldValue("")) } // État pour le numéro de téléphone
    var emailState by remember { mutableStateOf(TextFieldValue("")) } // État pour l'email


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
                .padding(horizontal = 35.dp)
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
                text = "Input your credentials",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = nameState,
                onValueChange = { newText -> nameState= newText },
                label = {
                    Text(
                        text = "Name",
                        color = Color(0xFFFF7622)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFF7622),
                    unfocusedBorderColor = Color(0xFFFF7622),
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp) // Hauteur du champ de texte
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
                    containerColor = Color.White
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
                value = textState,
                onValueChange = { newText -> textState = newText },
                label = {
                    Text(
                        text = "Email Id*",
                        color = Color(0xFFFF7622)
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFF7622),
                    unfocusedBorderColor = Color(0xFFFF7622),
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp) // Hauteur du champ de texte
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { newPassword -> password = newPassword },
                label = {
                    Text(
                        text = "Password*",
                        color = Color(0xFFFF7622)
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFF7622),
                    unfocusedBorderColor = Color(0xFFFF7622),
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                trailingIcon = {
                    val icon = if (passwordVisible) R.drawable.visible else R.drawable.invisibility
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { passwordVisible = !passwordVisible }
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))



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
                    text = "Login",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Action du bouton */ },
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth() // Assurez-vous que le bouton prend toute la largeur
                    .height(60.dp) // Hauteur du bouton
                    .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)) // Bordure grise
            ) {

                Text(
                    text = "Create an account instead",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

            }
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
fun PreviewCreer() {
    Creer()
}