package com.example.yalladrop.local.repo

import com.example.yalladrop.local.CartItem
import com.example.yalladrop.local.dao.CartDao
import kotlinx.coroutines.flow.Flow


public class CartRepository(private val cartDao: CartDao){
    val allCartItems : Flow<List<CartItem>> = cartDao.getCartItems()
    suspend fun addToCart(cartItem: CartItem) {
        cartDao.addToCart(cartItem)
    }

    suspend fun updateCartItem(cartItem: CartItem){
        cartDao.updateCartItem(cartItem)
    }

    suspend fun  removeFromCart(cartItem: CartItem){
       cartDao.removeFromCart(cartItem)
    }

    suspend fun clearCart(){
        cartDao.clearCart()
    }

}

