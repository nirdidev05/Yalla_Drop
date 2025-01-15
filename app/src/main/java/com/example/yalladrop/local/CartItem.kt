package com.example.yalladrop.local

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val menuItems: List<MenuItem>
)

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double
)

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey val id: String = "0",
    val menuItemId: Int,
    val menuItemName: String,
    val price: Double,
    val quantity: Int
)
