package com.example.saleshub.repository

import com.example.saleshub.data.SalesDao
import com.example.saleshub.model.Sale
import kotlinx.coroutines.flow.Flow

// SalesRepository.kt


class SalesRepository(private val salesDao: SalesDao) {

    fun getAllSales(): Flow<List<Sale>> = salesDao.getAllSales()

    suspend fun insertSale(sale: Sale) {
        salesDao.insertSale(sale)
    }

    suspend fun getSaleById(saleId: Int): Flow<Sale> {
        return salesDao.getSaleById(saleId)
    }
}