package com.example.yalladrop.api.meals


data class Meal(
    val _id: String, // ID of the meal
    val name: String,
    val description: String? = null,
    val price: Double,
    val category: String? = null,
    val calories: Int? = null,
    val ingredients: List<String> = emptyList(),
    val preparationTime: Int? = null,
    val rating: Double? = null,
    val reviews: Int? = null,
    val restaurant: String, // Restaurant ID
    val imageRes: String? = null
)
