package com.example.yalladrop.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.yalladrop.local.Order
import com.example.yalladrop.local.OrderStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    /*
    @Transaction
    @Query("SELECT * FROM orders ORDER BY orderDate DESC")
    fun getAllOrdersWithDetails(): Flow<List<OrderWithDetails>>

    @Transaction
    @Query("SELECT * FROM orders WHERE id = :orderId")
    fun getOrderWithDetails(orderId: Long): Flow<OrderWithDetails?>


     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order): Long

    @Query("UPDATE orders SET status = :status WHERE id = :orderId")
    suspend fun updateOrderStatus(orderId: Long, status: OrderStatus)

    @Delete
    suspend fun deleteOrder(order: Order)
}