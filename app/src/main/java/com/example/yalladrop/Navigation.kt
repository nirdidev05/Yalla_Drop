package com.example.yalladrop

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yalladrop.auth.CreateAccount
import com.example.yalladrop.auth.FistPage
import com.example.yalladrop.auth.LoginPge
import com.example.yalladrop.auth.LoginViaAcccount
import com.example.yalladrop.delivery.Payment
import com.example.yalladrop.orders.ActiveOrders
import com.example.yalladrop.orders.CancelOrder
import com.example.yalladrop.orders.CancelOrderAnimation
import com.example.yalladrop.orders.CanceledOrders
import com.example.yalladrop.orders.CompletedOrders
import com.example.yalladrop.delivery.ConfirmeOrder
import com.example.yalladrop.delivery.ConfirmedOrderAnimation
import com.example.yalladrop.delivery.OrderList
import com.example.yalladrop.orders.LeaveReview
import com.example.yalladrop.profile.DeliveryAdresses
import com.example.yalladrop.profile.NewAdress
import com.example.yalladrop.profile.Profile

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "FistPage"
    ) {
        composable("ActiveOrders") { ActiveOrders(navController) }
        composable("CanceledOrders") { CanceledOrders(navController) }
        composable("CompletedOrders") { CompletedOrders(navController) }
        composable("CancelOrderReasons") { CancelOrder(navController) }
        composable("LeaveReview") { LeaveReview(navController) }
        composable("CancelOrderAnimation") { CancelOrderAnimation(navController) }
        composable("ConfirmedOrderAnimation") { ConfirmedOrderAnimation(navController) }
        composable("ConfirmeOrder")  {  ConfirmeOrder(navController) }
        composable("FistPage")  {  FistPage(navController) }
        composable("LoginPge")  {  LoginPge(navController) }
        composable("LoginViaAcccount")  { LoginViaAcccount(navController ) }
        composable("CreateAccount")  { CreateAccount(navController ) }
        composable("Payment")  { Payment(navController ) }
        composable("OrderList")  { OrderList(navController ) }
        composable("Profile")  { Profile(navController ) }
        composable("DeliveryAdresses")  { DeliveryAdresses(navController ) }
        composable("NewAdress")  { NewAdress(navController ) }






    }
}

