package com.example.yalladrop

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Address.kt - Entity
@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val isDefault: Boolean = false
)

// AddressDao.kt - Data Access Object
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
}

// AddressDatabase.kt - Database
@Database(entities = [Address::class], version = 1)
abstract class AddressDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao

    companion object {
        @Volatile
        private var INSTANCE: AddressDatabase? = null

        fun getDatabase(context: Context): AddressDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AddressDatabase::class.java,
                    "address_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// AddressRepository.kt - Repository
class AddressRepository(private val addressDao: AddressDao) {
    val allAddresses: Flow<List<Address>> = addressDao.getAllAddresses()

    suspend fun insertAddress(address: Address) {
        addressDao.insertAddress(address)
    }

    suspend fun deleteAddress(address: Address) {
        addressDao.deleteAddress(address)
    }

    suspend fun setDefaultAddress(addressId: Long) {
        addressDao.clearDefaultAddress()
        addressDao.setDefaultAddress(addressId)
    }
}

// AddressViewModel.kt - ViewModel
class AddressViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AddressRepository
    val allAddresses: StateFlow<List<Address>>

    init {
        val addressDao = AddressDatabase.getDatabase(application).addressDao()
        repository = AddressRepository(addressDao)
        allAddresses = repository.allAddresses.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

    fun addAddress(street: String, city: String, state: String, zipCode: String) {
        viewModelScope.launch {
            val address = Address(
                street = street,
                city = city,
                state = state,
                zipCode = zipCode
            )
            repository.insertAddress(address)
        }
    }

    fun deleteAddress(address: Address) {
        viewModelScope.launch {
            repository.deleteAddress(address)
        }
    }

    fun setDefaultAddress(addressId: Long) {
        viewModelScope.launch {
            repository.setDefaultAddress(addressId)
        }
    }
}