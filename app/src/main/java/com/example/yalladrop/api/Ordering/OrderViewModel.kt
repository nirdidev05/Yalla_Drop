package com.example.yalladrop.api.Ordering
import ApiService
import OrderRequest
import OrderStatusUpdateRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yalladrop.api.restauration.Restaurant
import com.example.yalladrop.api.restauration.RestaurantState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class OrderState {
    object Idle : OrderState()
    object Loading : OrderState()
    data class Success(val message: String, val order: Order? = null , val orders: List<Order> = emptyList()) : OrderState()
    data class Error(val error: String) : OrderState()
}

class OrderViewModel(private val apiService: ApiService) : ViewModel() {

    private val _orderState = MutableStateFlow<OrderState>(OrderState.Idle)
    val orderState: StateFlow<OrderState> = _orderState

    fun resetOrderState() {
        _orderState.value = OrderState.Idle
    }

    fun fetchAllOrders() {
        viewModelScope.launch {
            _orderState.value = OrderState.Loading
            try {
                val response = apiService.getAllOrders()
                if (response.success) {
                    _orderState.value = OrderState.Success(
                        message = "Fetched restaurants successfully",
                        orders = response.orders

                    )
                } else {
                    _orderState.value = OrderState.Error(response.message ?: "Failed to fetch restaurants")
                }
            } catch (e: Exception) {
                _orderState.value = OrderState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    // Create a new order
    fun createOrder(orderRequest: OrderRequest) {
        viewModelScope.launch {
            _orderState.value = OrderState.Loading
            try {
                val response = apiService.createOrder(orderRequest)
                if (response.success) {
                    _orderState.value = OrderState.Success(
                        message = "Order created successfully",
                        order = response.order
                    )
                } else {
                    _orderState.value = OrderState.Error(
                        error = response.message ?: "Failed to create order"
                    )
                }
            } catch (e: Exception) {
                _orderState.value = OrderState.Error(
                    error = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
    }

    // Get an order by ID
    fun getOrderById(orderId: String) {
        viewModelScope.launch {
            _orderState.value = OrderState.Loading
            try {
                val response = apiService.getOrderById(orderId)
                if (response.success) {
                    _orderState.value = OrderState.Success(
                        message = "Order fetched successfully",
                        order = response.order
                    )
                } else {
                    _orderState.value = OrderState.Error(
                        error = response.message ?: "Failed to fetch order"
                    )
                }
            } catch (e: Exception) {
                _orderState.value = OrderState.Error(
                    error = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
    }

    // Update order status
    fun updateOrderStatus(orderId: String, status: String) {
        viewModelScope.launch {
            _orderState.value = OrderState.Loading
            try {
                val response = apiService.updateOrderStatus(
                    orderId,
                    OrderStatusUpdateRequest(status)
                )
                if (response.success) {
                    _orderState.value = OrderState.Success(
                        message = "Order status updated successfully",
                        order = response.order
                    )
                } else {
                    _orderState.value = OrderState.Error(
                        error = response.message ?: "Failed to update order status"
                    )
                }
            } catch (e: Exception) {
                _orderState.value = OrderState.Error(
                    error = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
    }

    // Delete an order
    fun deleteOrder(orderId: String) {
        viewModelScope.launch {
            _orderState.value = OrderState.Loading
            try {
                val response = apiService.deleteOrder(orderId)
                if (response.success) {
                    _orderState.value = OrderState.Success(
                        message = "Order deleted successfully",
                        order = null
                    )
                } else {
                    _orderState.value = OrderState.Error(
                        error = response.message ?: "Failed to delete order"
                    )
                }
            } catch (e: Exception) {
                _orderState.value = OrderState.Error(
                    error = e.localizedMessage ?: "An unknown error occurred"
                )
            }
        }
    }
}




class OrderViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrderViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
