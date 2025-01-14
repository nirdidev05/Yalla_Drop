package com.example.yalladrop.walid.nothing.walid
/*
import FoodDeliveryScreen
import FoodPage
import com.example.tdm.FoodDetailScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tdm.ui.theme.HomePage
import android.app.Application
import android.os.Build
import androidx.navigation.NavType
import androidx.navigation.navArgument
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Prefe.context = applicationContext

        setContent {

            val navController = rememberNavController()

            // Determine start destination based on saved login status
            val startDestination =
                if (Prefe.getEmail().isNullOrEmpty() || Prefe.getPassword().isNullOrEmpty()) {
                    "auth"
                } else {
                    "homePage"
                }

            NavHost(navController = navController, startDestination = startDestination) {
                composable("auth") { ConnectionPage(navController = navController) }
                composable("homePage") { HomePage(navController = navController) }
                composable("login") { CreateAccountScreen(navController = navController) }
                composable("foodPage") { FoodPage(navController = navController) }
                composable("grocerypage") { grocerypage() }
                composable("ecran2") { FoodDeliveryScreen(navController = navController) }
                composable(
                    "foodDetail/{foodId}",
                    arguments = listOf(navArgument("foodId") { type = NavType.IntType })
                ) { backStackEntry ->
                    FoodDetailScreen(
                        foodId = backStackEntry.arguments?.getInt("foodId") ?: return@composable,
                        navController = navController
                    )
                }
            }
        }
    }
}




 */
