package com.example.yalladrop.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yalladrop.models.PrincipaleBackGroound
import com.example.yalladrop.R
import com.example.yalladrop.models.TextFieldOutlined
import com.example.yalladrop.auth.validateName
import com.example.yalladrop.auth.validatePhone

@Composable
fun Profile(navController: NavHostController){

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



    PrincipaleBackGroound(title = "My profile" , navController ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 130.dp),
            horizontalAlignment =   Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(135.dp, 135.dp)
                    .clip(RoundedCornerShape(20))
                    .padding(vertical = 10.dp, horizontal = 15.dp)
            ) {




                Image(
                    painter = painterResource(id = R.drawable.images),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(7.dp)
                        .fillMaxWidth()

                        .align(Alignment.Center)
                        .clip(shape = RoundedCornerShape(20.dp)),
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(25.dp, 25.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color.Red),
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.cam),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(20.dp, 20.dp)
                            .clickable{

                            }
                    )
                }


            }
            Column {


                Text(
                    text = "Full Name",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 2.dp , top = 20.dp)

                )

                TextFieldOutlined(
                    value = nameValue,
                    placeholder = "Enter your name",
                    onChange = { setName(it) },
                    isError = nameError.isNotEmpty(),
                    errorMessage = nameError,
                    icon = Icons.Rounded.Person,
                    keyboardType = KeyboardType.Text,
                    unfocusedcontainercolor = MaterialTheme.colorScheme.secondary,
                    size = 20.dp,
                    unfocusedbordercolor = Color.Transparent

                )
                Text(
                    text = "Phone Number",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 2.dp , top = 20.dp)

                )
                TextFieldOutlined(
                    value = phoneValue,
                    placeholder = "Enter your phone number",
                    onChange = { setPhone(it) },
                    isError = phoneError.isNotEmpty(),
                    errorMessage = phoneError,
                    icon = Icons.Rounded.Phone,
                    unfocusedcontainercolor = MaterialTheme.colorScheme.secondary,
                    size = 20.dp,
                    keyboardType = KeyboardType.Phone,
                    unfocusedbordercolor = Color.Transparent
                )

            }

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        nameError = validateName(nameValue)
                        phoneError= validatePhone(phoneValue)

                        if(phoneError.isEmpty() && phoneValue.isEmpty())
                        {
                            println("correct")
                        }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(38)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),


                    ) {
                    Text(
                        text = "Update Profile",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color.White
                    )
                }
            }



        }

    }
}