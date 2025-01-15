package com.example.yalladrop.orders


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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.yalladrop.models.PrincipaleBackGroound
import com.example.yalladrop.R
import com.example.yalladrop.api.Ordering.Order
import com.example.yalladrop.api.Ordering.OrderViewModel
import com.example.yalladrop.api.Ordering.OrderViewModelFactory
import com.example.yalladrop.api.auth.RetrofitInstance
import com.example.yalladrop.api.restauration.Restaurant
import com.example.yalladrop.api.restauration.RestaurantState
import com.example.yalladrop.models.FoodCard
import com.example.yalladrop.models.FoodItems
import com.example.yalladrop.models.OrderListButton
import com.example.yalladrop.models.OrderState
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun ActiveOrders(navController: NavHostController) {

    val apiService = RetrofitInstance.api
    // Initialize OrderViewModel using ViewModelFactory
    val viewModel: OrderViewModel = viewModel(
        factory = OrderViewModelFactory(apiService)
    )
    val authState = viewModel.orderState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchAllOrders()
    }

    val allOrders : List<Order> = remember(authState.value) {
        when (val state = authState.value) {
            is com.example.yalladrop.api.Ordering.OrderState.Success -> state.orders ?: emptyList()
            else -> emptyList()
        }
    }
println(allOrders)

    val activeOrders : List<Order> = allOrders.filter { it.status == "Pending" || it.status == "Preparing" || it.status =="Picked Up" || it.status == "On the Way" }


    PrincipaleBackGroound(title = "My Orders" , navController  ){
        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OrderListButton(content = "Active", active = true, OrderState.PREPARING , navController)
                OrderListButton(content = "Completed", active = false, OrderState.COMPLETED , navController)
                OrderListButton(content = "Canceled", active = false,
                    OrderState.CANCELED , navController)
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

                        itemsIndexed(activeOrders) { index, item ->
                            FoodCard(
                                item = FoodItems("Pizza Heaven" , price = item.totalPrice , numItem = item.items.size , date = LocalDateTime.now() , painterId = R.drawable.test_foodcategory_pizza ,  ),
                                state = if(item.status == "Pending" ) {
                                    OrderState.PENDING
                                }else if(item.status == "Preparing" ) {
                                    OrderState.PREPARING
                                } else if(item.status == "Picked Up" ) {
                                    OrderState.PICKEDUP}
                                        else{
                                            OrderState.ONTHEWAY
                                },
                                navController,
                                if(list.size == index+1) true else false,
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

var list= listOf(
    FoodItems(name = "Coffee ZOUBIR" , price = 100.0 ,
        numItem = 2 ,
        date = LocalDateTime.now() ,
        painterId =  R.drawable.food_milkshakes
    ) ,
    FoodItems(name = "Coffee ZOUBIR" , price = 100.0 ,
        numItem = 2 ,
        date = LocalDateTime.now() ,
        painterId =  R.drawable.food_milkshakes
    ) ,
    FoodItems(name = "Coffee ZOUBIR" , price = 100.0 ,
        numItem = 2 ,
        date = LocalDateTime.now() ,
        painterId =  R.drawable.food_milkshakes
    ) ,
    )