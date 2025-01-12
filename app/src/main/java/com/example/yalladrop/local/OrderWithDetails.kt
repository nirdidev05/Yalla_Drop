package com.example.yalladrop.local

import androidx.room.Junction
import androidx.room.Relation

/*
data class OrderWithDetails(
    @Relation(
         parentColumn = "id",
         entityColumn = "restaurantId"
     )
     val restaurant: Restaurant,


     @Relation(
         entity = Item::class,
         parentColumn = "id",
         entityColumn = "id",
         associateBy = Junction(
             value = OrderItem::class,
             parentColumn = "orderId",
             entityColumn = "itemId"
         )
     )
     val items: List<Item>,
     @Relation(
         parentColumn = "id",
         entityColumn = "orderId"
     )
     val orderItems: List<OrderItem>


)

 */
