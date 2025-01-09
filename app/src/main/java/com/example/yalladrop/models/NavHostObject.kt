package com.example.yalladrop.models

import androidx.navigation.NavHostController

object NavRoutes {
    const val Login = "LoginViaAcccount"
    const val Main = "ActiveOrders"
    const val FirstPage = "FistPage"
}


object NavigationManager {
    lateinit var navController: NavHostController

    fun navigate(route: String) {
        navController.navigate(route)
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}