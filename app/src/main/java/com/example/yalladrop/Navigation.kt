package com.example.yalladrop

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: TextFieldViewModel = viewModel()

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
        composable("ConfirmeOrder")  {  ConfirmeOrder(navController)}
        composable("FistPage")  {  FistPage(navController)}
        composable("LoginPge")  {  LoginPge(navController)}
        composable("LoginViaAcccount")  { LoginViaAcccount(navController =  navController , viewModel = viewModel) }
        composable("CreateAccount")  { CreateAccount(navController =  navController , viewModel = viewModel) }



    }
}

