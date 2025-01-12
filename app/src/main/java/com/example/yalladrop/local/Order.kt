package com.example.yalladrop.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "orders",
    foreignKeys = [

       /* ForeignKey(
            entity = Restaurant::class,
            parentColumns = ["id"],
            childColumns = ["restaurantId"],
            onDelete = ForeignKey.RESTRICT
        )

        */
    ]
)
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val address: String,
    val restaurantId: Long,
    val totalPrice: Double,
    val orderDate: Long = System.currentTimeMillis(),
    val status: OrderStatus
)

enum class OrderStatus {
    PENDING, CONFIRMED, PREPARING, DELIVERING, DELIVERED, CANCELLED
}