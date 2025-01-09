package com.example.yalladrop.orders


import android.content.Context
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
import com.example.yalladrop.models.PrincipaleBackGroound
import com.example.yalladrop.R
import com.example.yalladrop.models.AuthViewModel
import com.example.yalladrop.models.FoodCard
import com.example.yalladrop.models.OrderListButton
import com.example.yalladrop.models.OrderState

@Composable
fun CompletedOrders(){
    PrincipaleBackGroound(title = "My Orders" ){
        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OrderListButton(content = "Active", active = false, OrderState.ACTIVE , )
                OrderListButton(content = "Completed", active = true, OrderState.COMPLETED , )
                OrderListButton(content = "Canceled", active = false,
                    OrderState.CANCELED , )
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
                                state = OrderState.COMPLETED,
                                if(list.size == index +1) true else false,
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

