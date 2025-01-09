package com.example.yalladrop

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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
import com.example.yalladrop.models.AuthViewModel
import com.example.yalladrop.models.NavRoutes
import com.example.yalladrop.orders.LeaveReview
import com.example.yalladrop.profile.DeliveryAdresses
import com.example.yalladrop.profile.NewAdress
import com.example.yalladrop.profile.Profile

@Composable
fun Navigation(navController: NavHostController , viewModel: AuthViewModel = hiltViewModel()) {

    if (viewModel.isUserLoggedIn()) {
        navController.navigate(NavRoutes.Main) {
            popUpTo(NavRoutes.FirstPage) { inclusive = true }
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (viewModel.isUserLoggedIn() == true) NavRoutes.Main else NavRoutes.FirstPage,
    ) {
        composable(NavRoutes.Main) { ActiveOrders()  }
        composable("CanceledOrders") { CanceledOrders()  }
        composable("CompletedOrders") { CompletedOrders( )  }
        composable("CancelOrderReasons") { CancelOrder() }
        composable("LeaveReview") { LeaveReview() }
        composable("CancelOrderAnimation") { CancelOrderAnimation() }
        composable("ConfirmedOrderAnimation") { ConfirmedOrderAnimation() }
        composable("ConfirmeOrder")  {  ConfirmeOrder() }
        composable(NavRoutes.FirstPage)  {  FistPage() }
        composable("LoginPge")  {  LoginPge() }
        composable(NavRoutes.Login)  { LoginViaAcccount(viewModel = viewModel ) }
        composable("CreateAccount")  { CreateAccount( ) }
        composable("Payment")  { Payment() }
        composable("OrderList")  { OrderList() }
        composable("Profile")  { Profile() }
        composable("DeliveryAdresses")  { DeliveryAdresses( ) }
        composable("NewAdress")  { NewAdress( ) }






    }
}

