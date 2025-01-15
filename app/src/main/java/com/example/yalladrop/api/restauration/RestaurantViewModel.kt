package com.example.yalladrop.api.restauration

import ApiService
import CreateRestaurantRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class RestaurantState {
    object Idle : RestaurantState()
    object Loading : RestaurantState()
    data class Success(val message: String, val restaurant: Restaurant? = null, val restaurants: List<Restaurant>? = null) : RestaurantState()
    data class Error(val error: String) : RestaurantState()
}

class RestaurantViewModel(private val apiService: ApiService) : ViewModel() {

    private val _restaurantState = MutableStateFlow<RestaurantState>(RestaurantState.Idle)
    val restaurantState: StateFlow<RestaurantState> = _restaurantState

    fun resetState() {
        _restaurantState.value = RestaurantState.Idle
    }

    // Fetch all restaurants
    fun fetchAllRestaurants() {
        viewModelScope.launch {
            _restaurantState.value = RestaurantState.Loading
            try {
                val response = apiService.getAllRestaurants()
                if (response.success) {
                    _restaurantState.value = RestaurantState.Success(
                        message = "Fetched restaurants successfully",
                        restaurants = response.restaurants
                    )
                } else {
                    _restaurantState.value = RestaurantState.Error(response.message ?: "Failed to fetch restaurants")
                }
            } catch (e: Exception) {
                _restaurantState.value = RestaurantState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    // Fetch a restaurant by ID
    fun fetchRestaurantById(id: String) {
        viewModelScope.launch {
            _restaurantState.value = RestaurantState.Loading
            try {
                val response = apiService.getRestaurantById(id)
                if (response.success) {
                    _restaurantState.value = RestaurantState.Success(
                        message = "Fetched restaurant successfully",
                        restaurant = response.restaurant
                    )
                } else {
                    _restaurantState.value = RestaurantState.Error(response.message ?: "Failed to fetch restaurant")
                }
            } catch (e: Exception) {
                _restaurantState.value = RestaurantState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    // Create a new restaurant
    fun createRestaurant(request: CreateRestaurantRequest) {
        viewModelScope.launch {
            _restaurantState.value = RestaurantState.Loading
            try {
                val response = apiService.createRestaurant(request)
                if (response.success) {
                    _restaurantState.value = RestaurantState.Success(
                        message = "Restaurant created successfully",
                        restaurant = response.restaurant
                    )
                } else {
                    _restaurantState.value = RestaurantState.Error(response.message ?: "Failed to create restaurant")
                }
            } catch (e: Exception) {
                _restaurantState.value = RestaurantState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    // Update a restaurant
    fun updateRestaurant(id: String, request: CreateRestaurantRequest) {
        viewModelScope.launch {
            _restaurantState.value = RestaurantState.Loading
            try {
                val response = apiService.updateRestaurant(id, request)
                if (response.success) {
                    _restaurantState.value = RestaurantState.Success(
                        message = "Restaurant updated successfully",
                        restaurant = response.restaurant
                    )
                } else {
                    _restaurantState.value = RestaurantState.Error(response.message ?: "Failed to update restaurant")
                }
            } catch (e: Exception) {
                _restaurantState.value = RestaurantState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    // Delete a restaurant
    fun deleteRestaurant(id: String) {
        viewModelScope.launch {
            _restaurantState.value = RestaurantState.Loading
            try {
                val response = apiService.deleteRestaurant(id)
                if (response.success) {
                    _restaurantState.value = RestaurantState.Success(message = "Restaurant deleted successfully")
                } else {
                    _restaurantState.value = RestaurantState.Error(response.message ?: "Failed to delete restaurant")
                }
            } catch (e: Exception) {
                _restaurantState.value = RestaurantState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}


class RestaurantViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RestaurantViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}