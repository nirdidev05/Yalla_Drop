package com.example.yalladrop.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yalladrop.local.Address
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {
    @Query("SELECT * FROM addresses")
    fun getAllAddresses(): Flow<List<Address>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: Address)

    @Delete
    suspend fun deleteAddress(address: Address)

    @Query("DELETE FROM addresses WHERE id = :addressId")
    suspend fun deleteAddressById(addressId: Long)

    @Query("UPDATE addresses SET isDefault = 0")
    suspend fun clearDefaultAddress()

    @Query("UPDATE addresses SET isDefault = 1 WHERE id = :addressId")
    suspend fun setDefaultAddress(addressId: Long)

    @Query("SELECT * FROM addresses WHERE id = :addressId LIMIT 1")
    fun getAddressById(addressId: Long): Flow<Address?>
}