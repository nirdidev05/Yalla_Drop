package com.example.yalladrop.profile

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.rounded.Place
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.yalladrop.models.PrincipaleBackGroound
import com.example.yalladrop.R
import com.example.yalladrop.local.viewmodels.AddressViewModel
import com.example.yalladrop.models.TextFieldOutlined


@Composable
fun NewAdress(navController: NavHostController ,  viewModel: AddressViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){

    val focusManager = LocalFocusManager.current
    var nameValue by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    fun setName(value: String){
        nameValue = value
    }

    var adressValue by remember { mutableStateOf("") }
    var adressError by remember { mutableStateOf("") }
    fun setAdress(value: String){
        adressValue = value
    }



    PrincipaleBackGroound(title = "Add New Address" , navController ){
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
                    painter = painterResource(id = R.drawable.homeicon),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(90.dp, 90.dp)
                        .align(Alignment.Center)
                )



            }
            Column {


                Text(
                    text = "Name",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 2.dp , top = 20.dp)

                )

                TextFieldOutlined(
                    value = nameValue,
                    placeholder = "Enter the name of your address",
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
                    text = "Address",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 2.dp , top = 20.dp)

                )
                TextFieldOutlined(
                    value = adressValue,
                    placeholder = "Enter the address",
                    onChange = { setAdress(it) },
                    isError = adressError.isNotEmpty(),
                    errorMessage = adressError,
                    icon = Icons.Rounded.Place,
                    unfocusedcontainercolor = MaterialTheme.colorScheme.secondary,
                    size = 20.dp,
                    keyboardType = KeyboardType.Text,
                    unfocusedbordercolor = Color.Transparent
                )

            }

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        if(nameValue.isEmpty())
                            nameError = "Give a label for your address"
                        else
                            nameError=""

                        if(adressValue.isEmpty())
                            adressError = "Give an address"
                        else
                            adressError = ""
                        if(adressError.isEmpty() && nameError.isEmpty())
                        {
                            viewModel.addAddress(name = nameValue, address = adressValue)
                            navController.navigateUp()
                        }

                    },
                    modifier = Modifier
                        .height(40.dp)
                        .width(250.dp)
                        .clip(RoundedCornerShape(38)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),


                    ) {
                    Text(
                        text = "Add new address",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color("#E95322".toColorInt())
                    )
                }
            }



        }

    }
}