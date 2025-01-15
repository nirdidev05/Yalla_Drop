package com.example.yalladrop.api.restauration

data class Restaurant(
    val _id: String, // Corresponds to MongoDB _id
    val name: String,
    val logo: String = "",
    val category: String = "",
    val cuisineType: List<String>,
    val averageRating: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val deliveryTime: String = "",
    val discount: Any? = null, // Can hold mixed types
    val reviewCount: Int = 0
)
