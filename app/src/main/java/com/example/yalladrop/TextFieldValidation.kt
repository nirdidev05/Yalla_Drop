package com.example.yalladrop

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController


@Composable
fun TextFieldValidation(
    value: String,
    placeholder: String,
    label: String,
    onChange: (String) -> Unit,
    isError: Boolean,
    icon: ImageVector,
    errorMessage: String,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {

    var showPassword by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if (!it.contains("\n"))
                    onChange(it)
            },
            placeholder = {
                Text(text = placeholder ,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color("#8E8E93".toColorInt()),
                )
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.displayMedium,
            label = {
                Text(text = label ,
                style = MaterialTheme.typography.labelMedium ,color = Color("#8E8E93".toColorInt()),)
            },

            trailingIcon = {
                if (isPassword){
                    Icon(
                        if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showPassword) "Show Password" else "Hide Password",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { showPassword = !showPassword }
                    )
                }else {
                    null
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                errorBorderColor = Color.Red,
                focusedLabelColor = MaterialTheme.colorScheme.primary ,
                unfocusedLabelColor = Color("#8E8E93".toColorInt()),
            ),
            shape = RoundedCornerShape(16.dp),
            visualTransformation = if (isPassword){
                if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
            } else { VisualTransformation.None },
            isError = isError
        )
        if (isError){
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 2.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}


class TextFieldViewModel: ViewModel() {
    var nameValue by mutableStateOf("")
        private set
    var nameError by mutableStateOf("")
        private set
    fun setName(value: String){
        nameValue = value
    }

    var phoneValue by mutableStateOf("")
        private set
    var phoneError by mutableStateOf("")
        private set
    fun setPhone(value: String){
        phoneValue = value
    }

    var emailValue by mutableStateOf("")
        private set
    var emailError by mutableStateOf("")
        private set
    fun setEmail(value: String){
        emailValue = value
    }
    var passwordValue by mutableStateOf("")
        private set
    var passwordError by mutableStateOf("")
        private set
    fun setPassword(value: String){
        passwordValue = value
    }

    var confirmPasswordValue by mutableStateOf("")
        private set
    var confirmPasswordError by mutableStateOf("")
        private set
    fun setConfirmPassword(value: String){
        confirmPasswordValue = value
    }

    private fun validateName(): Boolean {
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
        nameError = errorMessage
        return isValid
    }

    private fun validatePhone(): Boolean {
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
        phoneError = errorMessage
        return isValid
    }

    private fun validateEmail(): Boolean {
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
        emailError = errorMessage
        return isValid
    }

    private fun validatePassword(): Boolean {
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
        passwordError = errorMessage
        return isValid
    }


    private fun validateConfirmPassword(): Boolean {
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
        confirmPasswordError = errorMessage
        return isValid
    }


    fun validateForm(navController: NavHostController, destination :  String ) {
        if (validateName()  &&  validateEmail() && validatePhone() && validatePassword() && validateConfirmPassword()) {
            if(destination!= "")
            navController.navigate(destination){
                popUpTo(0) { inclusive = true } // Clear the entire back stack
            }
        }
    }
}