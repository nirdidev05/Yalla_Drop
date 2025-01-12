package com.example.yalladrop.local.repo

import com.example.yalladrop.local.Address
import com.example.yalladrop.local.dao.AddressDao
import kotlinx.coroutines.flow.Flow

public class AddressRepository(private val addressDao: AddressDao) {
    val allAddresses: Flow<List<Address>> = addressDao.getAllAddresses()

    suspend fun insertAddress(address: Address) {
        addressDao.insertAddress(address)
    }

    suspend fun deleteAddress(address: Address) {
        addressDao.deleteAddress(address)
    }

    suspend fun setDefaultAddress(addressId: Long) {
        addressDao.clearDefaultAddress()
        addressDao.setDefaultAddress(addressId)
    }

    fun getAddressById(addressId: Long): Flow<Address?> {
        return addressDao.getAddressById(addressId)
    }
}



