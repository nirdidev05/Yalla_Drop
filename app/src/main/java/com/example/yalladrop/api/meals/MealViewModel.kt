package com.example.yalladrop.api.meals

import ApiService
import MealRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yalladrop.api.restauration.Restaurant
import com.example.yalladrop.api.restauration.RestaurantState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class MealState {
    object Idle : MealState()
    object Loading : MealState()
    data class Success(val message: String, val meal: Meal? = null, val meals: List<Meal>? = null) : MealState()
    data class Error(val error: String) : MealState()
}

class MealViewModel(private val apiService: ApiService) : ViewModel() {

    private val _mealState = MutableStateFlow<MealState>(MealState.Idle)
    val mealState: StateFlow<MealState> = _mealState

    fun resetState() {
        _mealState.value = MealState.Idle
    }

    fun fetchAllMeals() {
        viewModelScope.launch {
            _mealState.value = MealState.Loading
            try {
                val response = apiService.getAllMeals()
                if (response.success) {
                    _mealState.value = MealState.Success("succeful" , meals = response.meals)
                } else {
                    _mealState.value = MealState.Error("Failed to fetch meals")
                }
            } catch (e: Exception) {
                _mealState.value = MealState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun fetchMealsByRestaurant(restaurantId: String) {
        viewModelScope.launch {
            _mealState.value = MealState.Loading
            try {
                val meals = apiService.getMealsByRestaurant(restaurantId)
                _mealState.value = MealState.Success("Succeful" ,null ,  meals)
            } catch (e: Exception) {
                _mealState.value = MealState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun createMeal(restaurantId: String, mealRequest: MealRequest) {
        viewModelScope.launch {
            _mealState.value = MealState.Loading
            try {
                val createdMeal = apiService.createMeal(restaurantId, mealRequest)
                _mealState.value = MealState.Success("Succeful" , null ,  listOf(createdMeal))
            } catch (e: Exception) {
                _mealState.value = MealState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun updateMeal(mealId: String, mealRequest: MealRequest) {
        viewModelScope.launch {
            _mealState.value = MealState.Loading
            try {
                val updatedMeal = apiService.updateMeal(mealId, mealRequest)
                _mealState.value = MealState.Success("Succeful" , null ,  listOf(updatedMeal))
            } catch (e: Exception) {
                _mealState.value = MealState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun deleteMeal(mealId: String) {
        viewModelScope.launch {
            _mealState.value = MealState.Loading
            try {
                apiService.deleteMeal(mealId)
                _mealState.value = MealState.Success("Succeful" , null ,  emptyList())
            } catch (e: Exception) {
                _mealState.value = MealState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}

class MealViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            return MealViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
