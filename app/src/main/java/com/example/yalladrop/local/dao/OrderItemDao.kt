package com.example.yalladrop.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yalladrop.local.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItem(orderItem: OrderItem): Long

    @Query("SELECT * FROM order_items WHERE orderId = :orderId")
    fun getOrderItemsByOrder(orderId: Long): Flow<List<OrderItem>>

    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItem)
}
