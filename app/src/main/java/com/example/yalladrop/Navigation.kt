package com.example.yalladrop

import FoodDeliveryScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yalladrop.auth.CreateAccount
import com.example.yalladrop.auth.FistPage
import com.example.yalladrop.auth.LoginPge
import com.example.yalladrop.auth.LoginViaAcccount
import com.example.yalladrop.auth.VerifyEmailScreen
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
import com.example.yalladrop.home.AllRestaurantsScreen
import com.example.yalladrop.home.DeliverySubscriptionScreen
import com.example.yalladrop.local.pref.AuthManager
import com.example.yalladrop.orders.LeaveReview
import com.example.yalladrop.profile.DeliveryAdresses
import com.example.yalladrop.profile.NewAdress
import com.example.yalladrop.profile.Profile
import com.example.yalladrop.home.HomePage
import com.example.yalladrop.profile.ContactUs
import com.example.yalladrop.restauration.CategoryItemsPage
import com.example.yalladrop.restauration.FoodPage
import com.example.yalladrop.restauration.FoodDetailScreen
import com.example.yalladrop.restauration.GroceryPage
import com.example.yalladrop.restauration.groceries

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
        composable("VerifyEmail?token={token}") { backStackEntry ->
            val token = backStackEntry.arguments?.getString("token") ?: ""
            VerifyEmailScreen(navController = navController, token = token)
        }
        composable("Payment")  { Payment(navController ) }
        composable("OrderList")  { OrderList(navController ) }
        composable("Profile")  { Profile(navController ) }
        composable("DeliveryAdresses")  { DeliveryAdresses(navController ) }
        composable("NewAdress")  { NewAdress(navController ) }
        composable("Notifications")  { Notifications(navController ) }
        composable("TrackDelivery?status={status}") { backStackEntry ->
            val status = backStackEntry.arguments?.getString("status") ?: ""
            TrackDelivery(navController = navController, status = status)
        }
        composable("ContactUs")  { ContactUs(navController ) }
        composable("HomePage")  { HomePage(navController ) }
        composable("FoodPage")  { FoodPage(navController ) }
        composable("grocery") { GroceryPage(navController = navController) }
        composable("DeliverySubscriptionScreen") { DeliverySubscriptionScreen(navController = navController) }
        composable("deliverySubscription") {
            DeliverySubscriptionScreen(navController = navController)
        }

// New routes for restaurant sections
        composable("allRestaurants") {
            AllRestaurantsScreen(navController = navController)
        }
        composable(
            route = "category_items/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            val category = groceries.find { it.categoryName == categoryName }
            CategoryItemsPage(
                selectedCategory = category,
                onBackClick = { navController.navigateUp() }
            )
        }
        composable(
            route = "foodDelivery/{restaurantId}/{restaurantName}/{photoResId}",
            arguments = listOf(
                navArgument("restaurantId") { type = NavType.StringType },
                navArgument("restaurantName") { type = NavType.StringType },
                navArgument("photoResId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            FoodDeliveryScreen(
                navController = navController,
                restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: "",
                restaurantName = backStackEntry.arguments?.getString("restaurantName") ?: "",
                restaurantImage = backStackEntry.arguments?.getInt("photoResId") ?: 0
            )
        }


        // Route for food detail with food ID
        composable(
            route = "foodDetail/{foodId}",
            arguments = listOf(
                navArgument("foodId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            FoodDetailScreen(
                foodId = backStackEntry.arguments?.getString("foodId") ?: "0",
                navController = navController
            )
        }
    }
}

