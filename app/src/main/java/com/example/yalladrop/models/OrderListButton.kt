package com.example.yalladrop.models

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun OrderListButton(content : String, active: Boolean, state: OrderState, navController: NavHostController){

    if (active)
        Button(onClick = {
            if (state== OrderState.PREPARING || state == OrderState.PENDING || state == OrderState.ONTHEWAY || state == OrderState.PICKEDUP)
            {
                navController.navigate("ActiveOrders")
            }
            else if (state== OrderState.COMPLETED || state == OrderState.REWIEWED)
            {
                navController.navigate("CompletedOrders")
            }
            else if (state== OrderState.CANCELED)
            {
                navController.navigate("CanceledOrders")
            }
        } ,

            modifier = Modifier
                .size(110.dp, 28.dp)
                .border(0.5.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(45)) // Apply border with the same rounded shape
                .clip(RoundedCornerShape(38))

        ) {
            Text(text = content , style = MaterialTheme.typography.labelMedium , color = Color.White)
        }
    else
        Button(onClick = {
            if (state== OrderState.PREPARING || state == OrderState.PENDING || state == OrderState.ONTHEWAY || state == OrderState.PICKEDUP)
            {
                navController.navigate("ActiveOrders")
            }
            else if (state== OrderState.COMPLETED || state == OrderState.REWIEWED)
            {
                navController.navigate("CompletedOrders")
            }
            else if (state== OrderState.CANCELED)
            {
                navController.navigate("CanceledOrders")
            }
        } ,

            modifier = Modifier
                .size(110.dp, 28.dp)
                .clip(RoundedCornerShape(38)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        ) {
            Text(text = content , style = MaterialTheme.typography.labelMedium ,color =  MaterialTheme.colorScheme.onBackground)
        }

}