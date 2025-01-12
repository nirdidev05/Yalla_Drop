package com.example.yalladrop.delivery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.yalladrop.models.PrincipaleBackGroound
import com.example.yalladrop.models.FoodCard
import com.example.yalladrop.models.FoodItems
import com.example.yalladrop.models.OrderState
import com.example.yalladrop.orders.list

@Composable
fun OrderList(navController: NavHostController){

    var listCard : List<FoodItems> = list


    PrincipaleBackGroound(title = "Payment" , navController ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Shipping Address" , style = MaterialTheme.typography.titleMedium, fontSize = 24.sp, modifier = Modifier.padding(5.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .clip(RoundedCornerShape(38))
                .background(MaterialTheme.colorScheme.secondary)
                .padding(vertical = 10.dp, horizontal = 15.dp)
            ){
                Text(
                    "adress[itemPosition.value]", style = MaterialTheme.typography.labelMedium ,
                )
            }

            Text(text = "Order Summary" , style = MaterialTheme.typography.displayMedium , fontWeight = FontWeight.Medium , fontSize = 20.sp,modifier = Modifier.padding(top = 20.dp ))
            HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 5.dp , bottom = 10.dp))



            listCard.forEach {  item ->
                FoodCard(item = item, state = OrderState.INPROGRESS, navController, lastItem =  false , )
            }

            Box (
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Button(
                    onClick = {
                        /*TODO*/
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .width(200.dp)
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
