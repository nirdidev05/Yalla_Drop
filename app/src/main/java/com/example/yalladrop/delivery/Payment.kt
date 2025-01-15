package com.example.yalladrop.delivery

import OrderRequest
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.yalladrop.api.auth.RetrofitInstance
import com.example.yalladrop.api.Ordering.OrderItem
import com.example.yalladrop.api.Ordering.OrderState
import com.example.yalladrop.api.Ordering.OrderViewModel
import com.example.yalladrop.api.Ordering.OrderViewModelFactory
import com.example.yalladrop.local.CartItem
import com.example.yalladrop.local.pref.AuthManager
import com.example.yalladrop.local.viewmodels.CartViewModel
import com.example.yalladrop.models.PrincipaleBackGroound

@Composable
fun Payment(navController: NavHostController, viewModelCart: CartViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
            context: Context = LocalContext.current,){

    val apiService = RetrofitInstance.api
    // Initialize OrderViewModel using ViewModelFactory
    val viewModel: OrderViewModel = viewModel(
        factory = OrderViewModelFactory(apiService)
    )
    val authState = viewModel.orderState.collectAsState()
    val authManager = remember { AuthManager(context) }
    val address = authManager.getAdr()

    var listCard : List<CartItem> = viewModelCart.allCartItems.collectAsState().value


    PrincipaleBackGroound(title = "Payment" , navController ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 100.dp)
        ) {
            Text(text = address.toString() , style = MaterialTheme.typography.titleMedium, fontSize = 24.sp, modifier = Modifier.padding(5.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .clip(RoundedCornerShape(38))
                .background(MaterialTheme.colorScheme.secondary)
                .padding(vertical = 10.dp, horizontal = 15.dp)
            ){
                Text(
                    address.toString(), style = MaterialTheme.typography.labelMedium ,
                )
            }

            Text(text = "Order Summary" , style = MaterialTheme.typography.displayMedium , fontWeight = FontWeight.Medium , fontSize = 20.sp,modifier = Modifier.padding(top = 20.dp ))
            HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 5.dp , bottom = 10.dp))


            Row (
                Modifier.fillMaxWidth() ,
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
                ){
                Column () {
                    listCard.forEach { item ->
                        Row {
                            Text(text = "${item.menuItemName}" , style = MaterialTheme.typography.labelSmall , fontSize = 14.sp , )
                            Text(text = "x ${item.quantity} items" ,  style = MaterialTheme.typography.labelSmall ,  fontSize = 14.sp , modifier = Modifier.padding(start = 10.dp , bottom = 5.dp) , color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
                Text(text = "${sunPrice(listCard)} DA" ,  style = MaterialTheme.typography.displayMedium , fontSize = 18.sp , fontWeight = FontWeight.SemiBold  , modifier = Modifier.padding(end = 20.dp))
            }
            HorizontalDivider(thickness = 1.5.dp , color = Color("#FFD8C7".toColorInt()) , modifier = Modifier.padding(top = 10.dp , bottom = 10.dp))
            Text(text = "Order Summary" , style = MaterialTheme.typography.displayMedium , fontWeight = FontWeight.Medium , fontSize = 20.sp,modifier = Modifier.padding(top = 20.dp, bottom = 5.dp ))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Estimated delivery time" , style = MaterialTheme.typography.labelSmall , fontSize = 14.sp , )
                Text(text = "25 min" ,  style = MaterialTheme.typography.displayMedium , fontSize = 18.sp , fontWeight = FontWeight.SemiBold  , modifier = Modifier.padding(end = 20.dp), color = MaterialTheme.colorScheme.primary)            }

            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Button(
                    onClick = {
                        val orderItems: List<OrderItem> = listCard.map { item ->
                        OrderItem(
                            meal = item.id,
                            quantity = item.quantity,
                            price = item.price
                        )
                    }
                        var order = OrderRequest(user = authManager.getUserID().toString() ,
                            restaurant = authManager.getCartRestaurantID().toString() ,
                            items = orderItems,
                            totalPrice = sunPrice(listCard),
                            deliveryAddress = address.toString() ,
                            )
                        viewModel.createOrder(order)
                        authManager.updateCartRestaurant(null , null)
                        authManager.updateCartNotEmpty(false)
                        authManager.updateADR(null)
                        viewModelCart.clearCart()
                        navController.navigate("ConfirmedOrderAnimation")
                        {

                                popUpTo(0) { inclusive = true }

                        }

                    },
                    modifier = Modifier
                        .height(40.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(38)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),


                    ) {
                    Text(
                        text = "Confirm Order",
                        style = MaterialTheme.typography.displayMedium,
                        color = Color("#E95322".toColorInt())
                    )
                }
            }


        }

    }

    when (val state = authState.value) {
        is OrderState.Loading -> {
            println("************************> Loading...")
        }
        is OrderState.Success -> {
            println("+++++++Success++++++")

            //authManager.saveUserSession(emailValue, (state as AuthState.Success).user?.name.toString() , (state as AuthState.Success).user?.phone.toString() , (state as AuthState.Success).user?._id.toString())
          /*  LaunchedEffect(Unit) {
                navController.navigate("VerifyEmail?token=$token") {
                    popUpTo("CreateAccount") { inclusive = true } // Clear the back stack
                }
            }

           */
            /* LaunchedEffect(Unit) { // Safely handle navigation
                 println("************************>success :  ${state.message}")
                 navController.navigate("HomePage") {
                     popUpTo("CreateAccount") { inclusive = true }
                 }
                 viewModel.resetAuthState() // Reset state to prevent loops
             }*/
        }
        is OrderState.Error -> {
            println("************************>error : ${state.error}")
        }
        else -> {}
    }
}

fun sunPrice(listCard : List<CartItem>):Double{
    var sum = 0.0 ;
    listCard.forEach { item -> sum = sum + item.price* item.quantity }
    return sum
}