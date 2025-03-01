package com.example.yalladrop.orders

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.yalladrop.models.CustomAreaText
import com.example.yalladrop.models.PrincipaleBackGroound

@Composable
fun CancelOrder(navController: NavHostController) {
    PrincipaleBackGroound(title = "Cancel Order" , navController ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceEvenly ,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Praesent pellentesque congue lorem, vel tincidunt tortor.",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            RadioGroupExample()
            Button(onClick = {
                navController.navigate("CancelOrderAnimation") {
                    popUpTo(0) { inclusive = true }
                }
            } ,modifier = Modifier
                .size(110.dp, 35.dp)
                .border(
                    0.5.dp,
                    MaterialTheme.colorScheme.onBackground,
                    RoundedCornerShape(45)
                ) // Apply border with the same rounded shape
                .clip(RoundedCornerShape(38))

            ) {
            Text(text = "Submit" , style = MaterialTheme.typography.labelMedium , color = Color.White)
        }

        }


    }
}


@Composable
fun RadioGroupExample() {
    val options = listOf("I changed my choice1", "I changed my choice", "I changed my choice2", "Other")
    val selectedOption = remember { mutableStateOf(options[0]) }
    val text = rememberSaveable { mutableStateOf("") }

    Column(

        horizontalAlignment = Alignment.Start
    ) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption.value == option,
                    onClick = { selectedOption.value = option }
                )
                Text(option, style = MaterialTheme.typography.displayMedium)
            }
        }
        CustomAreaText(text, selectedOption.value == "Other")



    }
}
