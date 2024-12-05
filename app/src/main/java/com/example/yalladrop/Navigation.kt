package com.example.yalladrop

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "ActiveOrders"
    ) {
        composable("ActiveOrders") { ActiveOrders(navController) }
        composable("CanceledOrders") { CanceledOrders(navController) }
        composable("CompletedOrders") { CompletedOrders(navController) }
        composable("CancelOrderReasons") { CancelOrder(navController) }
        composable("LeaveReview") { LeaveReview(navController) }
        composable("CancelOrderAnimation") { CancelOrderAnimation(navController) }
        composable("ConfirmedOrderAnimation") { ConfirmedOrderAnimation(navController) }
        composable("ConfirmeOrder")  {  ConfirmeOrder(navController)}


    }
}