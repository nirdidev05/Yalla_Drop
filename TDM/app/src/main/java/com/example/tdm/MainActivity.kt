package com.example.tdm

import CategoryItemsPage
import FoodDeliveryScreen
import UnoPartnershipPage
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.tdm.model.groceries
import com.example.tdm.ui.DessertPage
import com.example.tdm.ui.FoodPage
import com.example.tdm.ui.theme.HomePage

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Prefe.context = applicationContext

        setContent {
            val navController = rememberNavController()

            // Determine start destination based on saved login status
            val startDestination = if (Prefe.getEmail().isNullOrEmpty() || Prefe.getPassword().isNullOrEmpty()) {
                "auth"
            } else {
                "homePage"
            }

            NavHost(navController = navController, startDestination = startDestination) {
                composable("auth") {
                    ConnectionPage(navController = navController)
                }

                // HomePage with planName argument
                composable("homePage") {
                    HomePage(navController = navController, planName = "Get Subscription")
                }

                composable("uno") {
                    UnoPartnershipPage(navController = navController)
                }

                composable("login") {
                    CreateAccountScreen(navController = navController)
                }

                composable("foodPage") {
                    FoodPage(navController = navController)
                }

                composable("DessertPage") {
                    DessertPage(navController = navController)
                }

                composable("grocery") {
                    GroceryPage(navController = navController)
                }

                composable("deliverySubscription") {
                    DeliverySubscriptionScreen(navController = navController)
                }


                // Handle planName in the route
                composable("homepage/{planName}") { backStackEntry ->
                    val planName = backStackEntry.arguments?.getString("planName")
                    planName?.let { HomePage(navController = navController, planName = it) }
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
                        navArgument("restaurantId") { type = NavType.IntType },
                        navArgument("restaurantName") { type = NavType.StringType },
                        navArgument("photoResId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    FoodDeliveryScreen(
                        navController = navController,
                        restaurantId = backStackEntry.arguments?.getInt("restaurantId") ?: 0,
                        restaurantName = backStackEntry.arguments?.getString("restaurantName") ?: "",
                        restaurantImage = backStackEntry.arguments?.getInt("photoResId") ?: 0
                    )
                }

                composable(
                    route = "candyDelivery/{restaurantId}/{restaurantName}/{photoResId}",
                    arguments = listOf(
                        navArgument("restaurantId") { type = NavType.IntType },
                        navArgument("restaurantName") { type = NavType.StringType },
                        navArgument("photoResId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    CandyDeliveryScreen(
                        navController = navController,
                        restaurantId = backStackEntry.arguments?.getInt("restaurantId") ?: 0,
                        restaurantName = backStackEntry.arguments?.getString("restaurantName") ?: "",
                        restaurantImage = backStackEntry.arguments?.getInt("photoResId") ?: 0
                    )
                }

                composable(
                    route = "candyDetail/{foodId}",
                    arguments = listOf(
                        navArgument("foodId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    CandyDetailScreen(
                        foodId = backStackEntry.arguments?.getInt("foodId") ?: 0,
                        navController = navController
                    )
                }

                // Route for food detail with food ID
                composable(
                    route = "foodDetail/{foodId}",
                    arguments = listOf(
                        navArgument("foodId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    FoodDetailScreen(
                        foodId = backStackEntry.arguments?.getInt("foodId") ?: 0,
                        navController = navController
                    )
                }
            }
        }
    }
}
