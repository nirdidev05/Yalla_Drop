package com.example.yalladrop.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yalladrop.local.Restaurant
import kotlinx.coroutines.flow.Flow

// RestaurantDao.kt
@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants(): Flow<List<Restaurant>>

    @Query("SELECT * FROM restaurants WHERE id = :id")
    fun getRestaurantById(id: Long): Flow<Restaurant?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: Restaurant): Long

    @Delete
    suspend fun deleteRestaurant(restaurant: Restaurant)
}
