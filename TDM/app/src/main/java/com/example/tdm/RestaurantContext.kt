package com.example.tdm.data

import com.example.tdm.R
import com.example.tdm.model.Restaurant
object RestaurantContext {

    val restaurants = listOf(
        Restaurant(
            photoResId = R.drawable.imagestarbucks,
            name = "Gourmet Haven",
            rating = 4.5f,
            deliveryFee = "Free",
            deliveryTime = "30 min",
            specialties = listOf("Italian", "Pizza", "Dessert"),
            discount = 10
        ),
        Restaurant(
            photoResId = R.drawable.photo2,
            name = "Sweet Delights",
            rating = 4.8f,
            deliveryFee = "$5",
            deliveryTime = "25 min",
            specialties = listOf("Dessert", "Bakery", "Ice Cream"),
            discount = false
        ),
        Restaurant(
            photoResId = R.drawable.photo3,
            name = "Hot Dog Express",
            rating = 4.3f,
            deliveryFee = "Free",
            deliveryTime = "20 min",
            specialties = listOf("Hot Dog", "Fast Food", "Drinks"),
            discount = 15 // 15% discount
        ),
        Restaurant(
            photoResId = R.drawable.photo4,
            name = "Ocean's Catch",
            rating = 4.0f,
            deliveryFee = "$8",
            deliveryTime = "35 min",
            specialties = listOf("Fish Restaurant", "Healthy", "Sushi"),
            discount = false
        ),
        Restaurant(
            photoResId = R.drawable.photo5,
            name = "Taco Fiesta",
            rating = 4.7f,
            deliveryFee = "$3",
            deliveryTime = "30 min",
            specialties = listOf("Mexican", "Fast Food", "Drinks"),
            discount = 20 // 20% discount
        ),
        Restaurant(
            photoResId = R.drawable.photo6,
            name = "Vegan Vibes",
            rating = 4.6f,
            deliveryFee = "Free",
            deliveryTime = "25 min",
            specialties = listOf("Vegan", "Healthy", "Breakfast"),
            discount = 5 // 5% discount
        ),
        Restaurant(
            photoResId = R.drawable.photo7,
            name = "BBQ Kings",
            rating = 4.4f,
            deliveryFee = "$6",
            deliveryTime = "40 min",
            specialties = listOf("Barbecue", "Fast Food", "Drinks"),
            discount = false
        ),
        Restaurant(
            photoResId = R.drawable.photo8,
            name = "Mediterranean Bites",
            rating = 4.9f,
            deliveryFee = "$4",
            deliveryTime = "30 min",
            specialties = listOf("Middle Eastern", "Healthy", "Bakery"),
            discount = 10 // 10% discount
        ),
        Restaurant(
            photoResId = R.drawable.photo9,
            name = "Noodle House",
            rating = 4.2f,
            deliveryFee = "$7",
            deliveryTime = "35 min",
            specialties = listOf("Chinese", "Fast Food", "Drinks"),
            discount = false
        ),
        Restaurant(
            photoResId = R.drawable.photo10,
            name = "Sunrise Cafe",
            rating = 4.1f,
            deliveryFee = "$5",
            deliveryTime = "20 min",
            specialties = listOf("Breakfast", "Bakery", "Drinks"),
            discount = 8 // 8% discount
        )
    )
}
