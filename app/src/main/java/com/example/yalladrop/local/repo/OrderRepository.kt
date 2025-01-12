package com.example.yalladrop.local.repo

import com.example.yalladrop.local.Item
import com.example.yalladrop.local.Order
import com.example.yalladrop.local.OrderItem
import com.example.yalladrop.local.OrderStatus
import com.example.yalladrop.local.Restaurant
import com.example.yalladrop.local.dao.ItemDao
import com.example.yalladrop.local.dao.OrderDao
import com.example.yalladrop.local.dao.OrderItemDao
import com.example.yalladrop.local.dao.RestaurantDao


class OrderRepository(
    private val orderDao: OrderDao,
    private val orderItemDao: OrderItemDao,
    private val restaurantDao: RestaurantDao,
    private val itemDao: ItemDao
) {
    //val allOrders = orderDao.getAllOrdersWithDetails()

    suspend fun createOrder(
        address:    String,
        restaurantId: Long,
        items: List<Pair<Item, Int>> // Pair of Item and quantity
    ): Long {
        val totalPrice = items.sumOf { (item, quantity) -> item.price * quantity }

        val order = Order(
            address = address,
            restaurantId = restaurantId,
            totalPrice = totalPrice,
            status = OrderStatus.PENDING
        )

        val orderId = orderDao.insertOrder(order)

        items.forEach { (item, quantity) ->
            val orderItem = OrderItem(
                orderId = orderId,
                itemId = item.id,
                quantity = quantity,
                priceAtOrder = item.price
            )
            orderItemDao.insertOrderItem(orderItem)
        }

        return orderId
    }

   // fun getOrderWithDetails(orderId: Long) = orderDao.getOrderWithDetails(orderId)

    suspend fun updateOrderStatus(orderId: Long, status: OrderStatus) {
        orderDao.updateOrderStatus(orderId, status)
    }

    // Restaurant operations
    fun getAllRestaurants() = restaurantDao.getAllRestaurants()

    suspend fun addRestaurant(restaurant: Restaurant) =
        restaurantDao.insertRestaurant(restaurant)

    // Item operations
    fun getItemsByRestaurant(restaurantId: Long) =
        itemDao.getItemsByRestaurant(restaurantId)

    suspend fun addItem(item: Item) = itemDao.insertItem(item)
}

