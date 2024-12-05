package com.example.yalladrop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController

@Composable
fun Payment(navController: NavHostController){

    var listCard : List<FoodItems> = list

    PrincipaleBackGroound(title = "Payment" , navController ){
        Column(modifier = Modifier
            .fillMaxWidth()
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

            Text(text = "Order Summary" , style = MaterialTheme.typography.displayMedium , fontWeight = FontWeight.Medium , fontSize = 20.sp,modifier = Modifier.padding(top = 10.dp ))
            HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 5.dp , bottom = 10.dp))



            listCard.forEach {  item ->
                FoodCard(item = item, state = OrderState.INPROGRESS, navController, lastItem =  false)
            }

            }


    }
}