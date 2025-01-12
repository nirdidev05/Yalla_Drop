package com.example.yalladrop.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yalladrop.local.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items WHERE restaurantId = :restaurantId")
    fun getItemsByRestaurant(restaurantId: Long): Flow<List<Item>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItemById(id: Long): Flow<Item?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item): Long

    @Delete
    suspend fun deleteItem(item: Item)
}