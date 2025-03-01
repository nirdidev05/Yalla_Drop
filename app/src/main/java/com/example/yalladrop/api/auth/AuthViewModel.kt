package com.example.yalladrop.api.auth

import LoginRequest
import SignupRequest
import UpdateUserRequest
import VerifyRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String, val token: String, val user: User?) : AuthState() // Include token
    data class Error(val error: String) : AuthState()
}
class AuthViewModel : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }
    fun createUser(name: String, email: String, phone: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = RetrofitInstance.api.createUser(SignupRequest(name, email, phone, password))

                val token = response.token // Ensure your API returns the token

                _authState.value = AuthState.Success(response.message, token.toString() , response.user)
            } catch (e: Exception) {
               _authState.value = AuthState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = RetrofitInstance.api.login(LoginRequest(email, password))
                if (response.token != null) {
                    _authState.value = AuthState.Success(message = "Login successful" , token = "token" ,user = response.user)
                } else {
                    _authState.value = AuthState.Error("Invalid credentials")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
    fun updateUser(userId: String, name : String , phone : String ) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = RetrofitInstance.api.updateUser(userId, UpdateUserRequest(name , phone))
                _authState.value = AuthState.Success(response.message, response.token ?: "", response.user)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.localizedMessage ?: "Failed to update user")
            }
        }
    }

    fun verifyEmail(token: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = RetrofitInstance.api.verifyEmail(VerifyRequest(token))
                _authState.value = AuthState.Success(response.message , "token" , null)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
