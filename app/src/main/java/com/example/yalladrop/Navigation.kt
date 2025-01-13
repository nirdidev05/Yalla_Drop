package com.example.yalladrop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
import com.example.yalladrop.delivery.TrackDelivery
import com.example.yalladrop.local.pref.AuthManager
import com.example.yalladrop.orders.LeaveReview
import com.example.yalladrop.profile.DeliveryAdresses
import com.example.yalladrop.profile.NewAdress
import com.example.yalladrop.profile.Profile
import com.example.yalladrop.home.HomePage
import com.example.yalladrop.profile.ContactUs
import com.example.yalladrop.restauration.FoodDeliveryScreen
import com.example.yalladrop.restauration.FoodPage
import com.example.yalladrop.restauration.FoodDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val authManager = remember { AuthManager(context) }
    var isLoggedIn by remember { mutableStateOf(authManager.isLoggedIn()) }

    println("-------------------------------->$isLoggedIn")
    NavHost(
        navController = navController,
        startDestination =  if (isLoggedIn) "HomePage" else "FistPage"
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
        composable("CreateAccount")  { CreateAccount(navController = navController ) }
        composable("Payment")  { Payment(navController ) }
        composable("OrderList")  { OrderList(navController ) }
        composable("Profile")  { Profile(navController ) }
        composable("DeliveryAdresses")  { DeliveryAdresses(navController ) }
        composable("NewAdress")  { NewAdress(navController ) }
        composable("Notifications")  { Notifications(navController ) }
        composable("TrackDelivery")  { TrackDelivery(navController ) }
        composable("ContactUs")  { ContactUs(navController ) }
        composable("HomePage")  { HomePage(navController ) }
        composable("FoodDeliveryScreen")  { FoodDeliveryScreen(navController ) }
        composable("FoodPage")  { FoodPage(navController ) }
        composable("FoodDetailScreen")  { FoodDetailScreen ( navController = navController ) }
    }
}

