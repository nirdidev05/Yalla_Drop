package com.example.yalladrop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CanceledOrders(navController: NavHostController) {
    PrincipaleBackGroound(title = "My Orders" , navController ){
        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OrderListButton(content = "Active", active = false, OrderState.ACTIVE , navController)
                OrderListButton(content = "Completed", active = false, OrderState.COMPLETED , navController)
                OrderListButton(content = "Canceled", active = true,OrderState.CANCELED , navController)
            }

            if(!list.isEmpty())
                Column( modifier = Modifier.fillMaxHeight()) {
                    HorizontalDivider(
                        thickness = 1.5.dp,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 20.dp,
                            bottom = 15.dp
                        )
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxHeight()
                    ) {

                        itemsIndexed(list) { index, item ->
                            FoodCard(
                                item = item,
                                state = OrderState.CANCELED,
                                navController,
                                if(list.size == index +1) true else false
                            )
                        }
                    }
                }
            else{

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(id = R.drawable.empty),
                        contentDescription = null,
                    )
                    Box (
                        modifier = Modifier.fillMaxWidth().height(200.dp).padding(horizontal = 40.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "You don't have any active orders at this time",
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }







        }
    }
}

