package com.example.yalladrop.delivery

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.yalladrop.Address
import com.example.yalladrop.AddressViewModel
import com.example.yalladrop.R
import com.example.yalladrop.models.PrincipaleBackGroound
import com.example.yalladrop.models.FoodCard
import com.example.yalladrop.models.FoodItems
import com.example.yalladrop.models.OrderState
import com.example.yalladrop.orders.list

@Composable
fun ConfirmeOrder(navController:NavHostController, viewModel: AddressViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),){

    val addresses = viewModel.allAddresses.collectAsState(initial = emptyList()).value

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    val itemPosition = remember {
        mutableStateOf("Choose an address")
    }

    var listCard : List<FoodItems> = list

    PrincipaleBackGroound(title = "Confirm Order" , navController ){

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 100.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Shipping Address" , style = MaterialTheme.typography.titleMedium, fontSize = 24.sp, modifier = Modifier.padding(5.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .clip(RoundedCornerShape(38))
                .background(MaterialTheme.colorScheme.secondary)
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .clickable { isDropDownExpanded.value = true }
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        itemPosition.value , style = MaterialTheme.typography.labelMedium,
                    )
                    Icon(
                         Icons.Default.KeyboardArrowDown ,
                        contentDescription = "DropDown",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(30.dp)

                    )

                }
                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(fraction = 0.8f),
                    expanded = isDropDownExpanded.value,
                    onDismissRequest = {
                        isDropDownExpanded.value = false
                    }) {
                    addresses.forEachIndexed { index , adr ->
                        DropdownMenuItem(text = {
                            Text(text = adr.name )
                        },
                            onClick = {
                                isDropDownExpanded.value = false
                                itemPosition.value = adr.name
                            })
                    }
                }
            }

            Text(text = "Order Summary" , style = MaterialTheme.typography.displayMedium , fontWeight = FontWeight.Medium , fontSize = 20.sp , modifier = Modifier.padding(top = 15.dp))
            HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 5.dp , bottom = 10.dp))



                    listCard.forEach {  item ->
                        FoodCard(item = item, state = OrderState.INPROGRESS, navController, lastItem =  false )
                    }



            Box (
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Button(
                    onClick = {
                        navController.navigate("Payment")
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(38)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),


                    ) {
                    Text(
                        text = "Place Order",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color("#E95322".toColorInt())
                    )
                }
            }




        }

    }
}