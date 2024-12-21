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
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.offset


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpPage() {
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
                        text = "Help",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        fontSize = 32.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(100.dp))
                Divider(color = Color(0xFFFFD8C7), thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Ajuste l'espacement vertical pour un meilleur alignement
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Help with the order",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "support",
                            fontSize = 14.sp,
                            color = Color.Black
                        )

                    }

                    Image(
                        painter = painterResource(id = R.drawable.notback),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                // Action pour le retour
                            }
                    )


                }

                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color(0xFFFFD8C7), thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Ajuste l'espacement vertical pour un meilleur alignement
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Help center",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "General Information",
                            fontSize = 14.sp,
                            color = Color.Black
                        )

                    }

                    Image(
                        painter = painterResource(id = R.drawable.notback),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                // Action pour le retour
                            }
                    )



                }
                Spacer(modifier = Modifier.height(10.dp))

                Divider(color = Color(0xFFFFD8C7), thickness = 1.dp)









            }
            }


        }
    }



@Preview(showBackground = true)
@Composable
fun PreviewHelpPage() {
    HelpPage()
}



