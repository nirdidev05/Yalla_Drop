package com.example.projet_tdm

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.border
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import java.io.InputStream
import android.graphics.BitmapFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Noorder() {


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

            Spacer(modifier = Modifier.height(16.dp))

            // Image `icondoc`
            Image(
                painter = painterResource(id = R.drawable.icondoc),
                contentDescription = " ",
                modifier = Modifier
                    .size(200.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .offset(x = 95.dp, y = 250.dp)
            )

            // Texte après `icondoc`
            Text(
                text = "You don't have any active orders at this time",
                fontSize = 26.sp,
                color = Color(0xFFFF7622),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .offset(y = 250.dp)
            )

            // Boîte contenant les boutons
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.offset(y = (-470).dp), // Ajustez cette valeur pour positionner les boutons verticalement
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
fun PreviewNoorder() {
    Noorder()
}