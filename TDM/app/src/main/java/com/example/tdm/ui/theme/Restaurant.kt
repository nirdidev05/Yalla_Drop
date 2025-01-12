package com.example.tdm.model

data class Restaurant(
    val photoResId: Int,
    val name: String,
    val rating: Float,
    val deliveryFee: String,
    val deliveryTime: String,
    val specialties: List<String>,
    val discount: Any
)
