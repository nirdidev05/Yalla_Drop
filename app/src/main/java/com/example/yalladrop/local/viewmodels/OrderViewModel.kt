package com.example.yalladrop.local.viewmodels
/*
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.yalladrop.local.AppDatabase
import com.example.yalladrop.local.Item
import com.example.yalladrop.local.OrderStatus
import com.example.yalladrop.local.Restaurant
import com.example.yalladrop.local.repo.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: OrderRepository
   // val allOrders: StateFlow<List<OrderWithDetails>>
    val allRestaurants: StateFlow<List<Restaurant>>

    init {
        val database = AppDatabase.getDatabase(application)
        repository = OrderRepository(
            database.orderDao(),
            database.orderItemDao(),
            database.restaurantDao(),
            database.itemDao()
        )
/*
        allOrders = repository.allOrders.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


 */
        allRestaurants = repository.getAllRestaurants().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

    fun createOrder(address: String, restaurantId: Long, items: List<Pair<Item, Int>>) {
        viewModelScope.launch {
            repository.createOrder(address, restaurantId, items)
        }
    }

    fun updateOrderStatus(orderId: Long, status: OrderStatus) {
        viewModelScope.launch {
            repository.updateOrderStatus(orderId, status)
        }
    }
/*
    fun getOrderDetails(orderId: Long): Flow<OrderWithDetails?> {
        return repository.getOrderWithDetails(orderId)
    }


 */
    fun getItemsByRestaurant(restaurantId: Long): Flow<List<Item>> {
        return repository.getItemsByRestaurant(restaurantId)
    }

    fun addRestaurant(name: String, logo: String) {
        viewModelScope.launch {
            val restaurant = Restaurant(name = name, logo = logo)
            repository.addRestaurant(restaurant)
        }
    }

    fun addItem(name: String, price: Double, picture: String, restaurantId: Long) {
        viewModelScope.launch {
            val item = Item(
                name = name,
                price = price,
                picture = picture,
                restaurantId = restaurantId
            )
            repository.addItem(item)
        }
    }
}


 */