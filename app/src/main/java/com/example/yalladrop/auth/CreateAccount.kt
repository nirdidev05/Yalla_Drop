package com.example.yalladrop.auth

import android.content.Context
import android.util.Patterns
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.yalladrop.local.pref.AuthManager
import com.example.yalladrop.models.TextFieldOutlined


@Composable
fun CreateAccount(
    viewModel: AuthViewModel = viewModel(),
    context: Context = LocalContext.current,
    navController: NavHostController,
) {
    val authState = viewModel.authState.collectAsState()
    val authManager = remember { AuthManager(context) }


    val focusManager = LocalFocusManager.current
    var nameValue by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    fun setName(value: String){
        nameValue = value
    }

    var phoneValue by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf("") }
    fun setPhone(value: String){
        phoneValue = value
    }

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

    var confirmPasswordValue by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }
    fun setConfirmPassword(value: String){
        confirmPasswordValue = value
    }



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 20.dp)
        .padding(20.dp),) {
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
                    .clickable {
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
            TextFieldOutlined(
                value = nameValue,
                onChange = {setName(it)},
                placeholder = "Enter your Name",
                isError = nameError.isNotEmpty(),
                icon = Icons.Rounded.Person,
                errorMessage = nameError,
                keyboardType = KeyboardType.Text,
                label = "Name",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldOutlined(
                value = emailValue,
                onChange = { setEmail(it)},
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
                value = phoneValue,
                onChange = { setPhone(it)},
                placeholder = "Enter your phone number",
                isError = phoneError.isNotEmpty(),
                icon = Icons.Rounded.Phone,
                errorMessage = phoneError,
                keyboardType = KeyboardType.Number,
                label = "Phone number",
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
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldOutlined(
                value = confirmPasswordValue,
                onChange = {setConfirmPassword(it)},
                placeholder = "Confirm your password",
                isError = confirmPasswordError.isNotEmpty(),
                icon = Icons.Rounded.Password,
                isPassword = true,
                errorMessage = confirmPasswordError,
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
                        nameError= validateName(nameValue)
                        if(nameError.isEmpty())
                        {
                            emailError = validateEmail(emailValue)
                            if(emailError.isEmpty())
                            {
                                phoneError = validatePhone(phoneValue)
                                if(phoneError.isEmpty())
                                {
                                    passwordError= validatePassword(passwordValue)
                                    if(passwordError.isEmpty())
                                    {
                                        confirmPasswordError = validateConfirmPassword(confirmPasswordValue,passwordValue)
                                        if(confirmPasswordError.isEmpty())
                                        {
                                            viewModel.createUser(nameValue.toString(), emailValue.toString(),phoneValue.toString() ,  passwordValue.toString()) ;

                                        }
                                    }
                                }

                            }

                        }

                        {

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
    when (val state = authState.value) {
        is AuthState.Loading -> {
            println("************************> Loading...")
        }
        is AuthState.Success -> {

            val token = state.token
            authManager.saveUserSession(emailValue, (state as AuthState.Success).user?.name.toString() , (state as AuthState.Success).user?.phone.toString() , (state as AuthState.Success).user?._id.toString())
             LaunchedEffect(Unit) {
                navController.navigate("VerifyEmail?token=$token") {
                    popUpTo("CreateAccount") { inclusive = true } // Clear the back stack
                }
            }
           /* LaunchedEffect(Unit) { // Safely handle navigation
                println("************************>success :  ${state.message}")
                navController.navigate("HomePage") {
                    popUpTo("CreateAccount") { inclusive = true }
                }
                viewModel.resetAuthState() // Reset state to prevent loops
            }*/
        }
        is AuthState.Error -> {
            println("************************>error : ${state.error}")
        }
        else -> {}
    }
}


 fun validateName(nameValue : String): String {
    val name= nameValue.trim()
    var isValid = true
    var errorMessage = ""
    if (name.isBlank() || name.isEmpty()) {
        errorMessage = "Please fill name field"
        isValid = false
    } else if( !( name.matches(Regex("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) && name.filter { it.isLetter() }.length >= 3) ){
        errorMessage = "Wrong name Format"
        isValid = false
    }
   return errorMessage
}

 fun validatePhone(phoneValue : String): String {
    val phone= phoneValue.trim()
    var isValid = true
    var errorMessage = ""
    if (phone.isBlank() || phone.isEmpty()) {
        errorMessage = "Please fill your phone number"
        isValid = false
    } else if( !phone.matches(Regex("^(\\+213[567]|0[567])\\d{8}$")) ){
        errorMessage = "Wrong phone number Format"
        isValid = false
    }
   return errorMessage
}

 fun validateEmail(emailValue : String): String {
    val email = emailValue.trim()
    var isValid = true
    var errorMessage = ""
    if (email.isBlank() || email.isEmpty()) {
        errorMessage = "Please fill email field"
        isValid = false
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        errorMessage = "Wrong email Format"
        isValid = false
    }
    return errorMessage
}

 fun validatePassword(passwordValue : String): String {
    val password = passwordValue
    var isValid = true
    var errorMessage = ""

    if (password.isBlank() || password.isEmpty()) {
        errorMessage = "Please fill password field"
        isValid = false
    } else if (password.length < 8) {
        errorMessage = "Password must more than 8 character"
        isValid = false
    }
    return errorMessage
}

private fun validateConfirmPassword(confirmPasswordValue : String , passwordValue: String): String {
    val confirmPassword = confirmPasswordValue
    var isValid = true
    var errorMessage = ""

    if (confirmPassword.isBlank() || confirmPassword.isEmpty()) {
        errorMessage = "Please confirm your password"
        isValid = false
    } else if (confirmPassword != passwordValue) {
        errorMessage = "Incorrect confirmation"
        isValid = false
    }
    return errorMessage
}