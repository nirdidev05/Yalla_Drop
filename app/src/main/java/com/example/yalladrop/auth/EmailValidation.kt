package com.example.yalladrop.auth


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yalladrop.R
import com.example.yalladrop.api.auth.AuthState
import com.example.yalladrop.api.auth.AuthViewModel

@Composable
fun VerifyEmailScreen(viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), token: String, navController: NavHostController) {
    val authState by viewModel.authState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.verifyEmail(token)
    }

    when (authState) {
        is AuthState.Loading -> Text("Verifying...")
        is AuthState.Success -> {
            Text("Verification successful!")
            LaunchedEffect(Unit) {
                navController.navigate("HomePage") { popUpTo(0) }
            }
        }
        is AuthState.Error -> Text("Error: ${(authState as AuthState.Error).error}")
        else -> {}
    }
}

@Composable
fun EmailValidationPage() {

    Column(modifier = Modifier.fillMaxSize().padding(top = 20.dp).padding(20.dp),) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_arrowleft), // Replace with your image resource
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .padding(end = 10.dp)
                    .clickable{
                        println("Text clicked!")
                    },
            )
            Text(text = "Email validation" , style = MaterialTheme.typography.titleMedium , fontSize = 22.sp)
        }
        Spacer(modifier = Modifier.height(25.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Input your credentials",
                    modifier = Modifier.padding(bottom = 5.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.displayLarge,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = {
                    },

                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(16.dp)
                ) {


                    Text(
                        text = "Login",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )


                }





        }
    }
}

