package com.example.yalladrop.api.Ordering

class Order (

val _id: String, // Corresponds to MongoDB _id field
val user: String, // User ID
val restaurant: String, // Restaurant ID
val items: List<OrderItem>,
val totalPrice: Double,
val deliveryAddress: String,
val deliveryNotes: String? = null,
val status: String = "Pending",
val createdAt: String

)

data class OrderItem(
    val meal: String, // Meal ID
    val quantity: Int,
    val price: Double,
    val notes: String = ""
)