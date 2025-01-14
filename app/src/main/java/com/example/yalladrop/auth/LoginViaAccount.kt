package com.example.yalladrop.auth

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.yalladrop.R
import com.example.yalladrop.api.auth.AuthState
import com.example.yalladrop.api.auth.AuthViewModel
import com.example.yalladrop.api.auth.User
import com.example.yalladrop.local.pref.AuthManager
import com.example.yalladrop.models.TextFieldOutlined

@Composable
fun LoginViaAcccount(
    navController: NavHostController,
    context: Context = LocalContext.current,
    viewModel: AuthViewModel = viewModel(),

    ) {

    val authState = viewModel.authState.collectAsState()

    val focusManager = LocalFocusManager.current
    val authManager = remember { AuthManager(context) }

    var emailValue by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    fun setEmail(value: String){
        emailValue = value
    }
    var passwordValue by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    fun setPassword(value: String){
        passwordValue = value
    }

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

                            navController.navigate("LoginPge") {
                                popUpTo(0) { inclusive = true } // Clear the entire back stack
                            }

                    },
            )
            Text(text = "Login via an Account" , style = MaterialTheme.typography.titleMedium , fontSize = 22.sp)
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
            TextFieldOutlined(
                value = emailValue,
                onChange = {setEmail(it)},
                placeholder = "Enter your email",
                isError = emailError.isNotEmpty(),
                icon = Icons.Rounded.Email,
                errorMessage = emailError,
                keyboardType = KeyboardType.Email,
                label = "Email",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldOutlined(
                value = passwordValue,
                onChange = {setPassword(it)},
                placeholder = "Enter your password",
                isError = passwordError.isNotEmpty(),
                icon = Icons.Rounded.Password,
                isPassword = true,
                errorMessage = passwordError,
                label = "Password",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(25.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        emailError = validateEmail(emailValue)
                        passwordError= validatePassword(passwordValue)
                        if(emailError.isEmpty() && passwordError.isEmpty())
                        {
                            viewModel.login(emailValue , passwordValue)
                        }






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
                Button(
                    onClick = {

                        if (emailError.isEmpty() && passwordError.isEmpty()) {
                            navController.navigate("CreateAccount") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .border(
                            2.dp,
                            Color("#8E8E93".toColorInt()),
                            RoundedCornerShape(25)
                        ) // Apply border with the same rounded shape
                        .clip(RoundedCornerShape(20)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(20.dp)

                ) {

                    Text(
                        text = "Create an account",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displayMedium,
                        color = Color("#8E8E93".toColorInt()),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

            }


        }
    }

        when (val state = authState.value) {
            is AuthState.Loading -> {println("Logging in...")}
            is AuthState.Success -> {
                authManager.saveUserSession(emailValue, (state as AuthState.Success).user?.name.toString() , (state as AuthState.Success).user?.phone.toString() , (state as AuthState.Success).user?._id.toString())
                println("Success : ${state.message}")
                LaunchedEffect(Unit) {
                    navController.navigate("HomePage") {
                        popUpTo("LoginScreen") { inclusive = true }
                    }
                }
            }
            is AuthState.Error -> {
                println("Error: ${state.error}")
                if(state.error.toString() == "HTTP 401 Unauthorized")
                {
                    emailError = "   " ;
                    passwordError = "Incorrect email or password";
                }
            }
            else -> {}
        }



}

