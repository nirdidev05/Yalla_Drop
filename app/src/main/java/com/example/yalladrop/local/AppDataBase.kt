package com.example.yalladrop.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yalladrop.local.dao.AddressDao
import com.example.yalladrop.local.dao.CartDao


/*
@Database(
    entities = [
        Address::class,
        Restaurant::class,
        Item::class,
        Order::class,
        OrderItem::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
    abstract fun restaurantDao(): RestaurantDao
    abstract fun itemDao(): ItemDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

 */


// AddressDatabase.kt - Database
@Database(entities = [Address::class , CartItem::class] , version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
    abstract fun cartDao() : CartDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "address_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

