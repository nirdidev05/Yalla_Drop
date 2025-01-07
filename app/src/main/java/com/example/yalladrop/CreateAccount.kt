package com.example.yalladrop

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
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController


@Composable
fun CreateAccount(
    navController: NavHostController,
    viewModel: TextFieldViewModel
) {
    val focusManager = LocalFocusManager.current



    Column(modifier = Modifier.fillMaxSize().padding(top = 20.dp).padding(20.dp),) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrowleft), // Replace with your image resource
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
            Text(text = "Create an account" , style = MaterialTheme.typography.titleMedium , fontSize = 22.sp)
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
            TextFieldValidation(
                value = viewModel.nameValue,
                onChange = viewModel::setName,
                placeholder = "Enter your Name",
                isError = viewModel.nameError.isNotEmpty(),
                icon = Icons.Rounded.Person,
                errorMessage = viewModel.nameError,
                keyboardType = KeyboardType.Text,
                label = "Name",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldValidation(
                value = viewModel.emailValue,
                onChange = viewModel::setEmail,
                placeholder = "Enter your email",
                isError = viewModel.emailError.isNotEmpty(),
                icon = Icons.Rounded.Email,
                errorMessage = viewModel.emailError,
                keyboardType = KeyboardType.Email,
                label = "Email",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldValidation(
                value = viewModel.phoneValue,
                onChange = viewModel::setPhone,
                placeholder = "Enter your phone number",
                isError = viewModel.phoneError.isNotEmpty(),
                icon = Icons.Rounded.Phone,
                errorMessage = viewModel.phoneError,
                keyboardType = KeyboardType.Number,
                label = "Phone number",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldValidation(
                value = viewModel.passwordValue,
                onChange = viewModel::setPassword,
                placeholder = "Enter your password",
                isError = viewModel.passwordError.isNotEmpty(),
                icon = Icons.Rounded.Password,
                isPassword = true,
                errorMessage = viewModel.passwordError,
                label = "Password",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldValidation(
                value = viewModel.confirmPasswordValue,
                onChange = viewModel::setConfirmPassword,
                placeholder = "Confirm your password",
                isError = viewModel.confirmPasswordError.isNotEmpty(),
                icon = Icons.Rounded.Password,
                isPassword = true,
                errorMessage = viewModel.confirmPasswordError,
                label = "Confirm password",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(25.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        viewModel.validateForm(navController , "")

                    },

                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(16.dp)
                ) {


                    Text(
                        text = "Create an account",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )


                }
                Button(
                    onClick = {
                        navController.navigate("LoginViaAcccount")

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
                        text = "Login",
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
}

