package com.example.yalladrop.local.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.yalladrop.local.Address
import com.example.yalladrop.local.AppDataBase
import com.example.yalladrop.local.repo.AddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

public class AddressViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AddressRepository
    val allAddresses: StateFlow<List<Address>>

    init {
        val addressDao = AppDataBase.getDatabase(application).addressDao()
        repository = AddressRepository(addressDao)
        allAddresses = repository.allAddresses.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

    fun addAddress(address: String, name: String,
        //   state: String, zipCode: String
    ) {
        viewModelScope.launch {
            val address = Address(
                address = address,
                name = name,
                // state = state,
                // zipCode = zipCode
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

    fun getAddressById(addressId: Long): Flow<Address?> {
        return repository.getAddressById(addressId)
    }
}