package com.example.yalladrop.local.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.yalladrop.local.AppDataBase
import com.example.yalladrop.local.CartItem
import com.example.yalladrop.local.repo.CartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


public class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CartRepository
    val allCartItems : StateFlow<List<CartItem>>

    init {
        val cartDao = AppDataBase.getDatabase(application).cartDao()
        repository = CartRepository(cartDao)
        allCartItems = repository.allCartItems.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

    fun addToCart(id: String, menuItem: Int
                  , menuItemName: String
                  , price : Double
                  , quantity : Int
    ) {
        viewModelScope.launch {
            val cartItem = CartItem(
                id = id,
                menuItemId = menuItem,
                menuItemName = menuItemName ,
                price = price ,
                quantity = quantity
            )
            repository.addToCart(cartItem)
        }
    }

    fun updateCartItem(id: String, menuItem: Int
                       , menuItemName: String
                       , price : Double
                       , quantity : Int) {
        viewModelScope.launch {
            val cartItem = CartItem(
                id = id,
                menuItemId = menuItem,
                menuItemName = menuItemName ,
                price = price ,
                quantity = quantity
            )
            repository.updateCartItem(cartItem)
        }
    }

    fun removeFromCart(id: String, menuItem: Int
                       , menuItemName: String
                       , price : Double
                       , quantity : Int) {
        viewModelScope.launch {
            val cartItem = CartItem(
                id = id,
                menuItemId = menuItem,
                menuItemName = menuItemName ,
                price = price ,
                quantity = quantity
            )
            repository.removeFromCart(cartItem)
        }
    }

    fun clearCart() {
       viewModelScope.launch {
           repository.clearCart()
        }

    }
}