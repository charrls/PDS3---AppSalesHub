package com.example.saleshub.repository

import com.example.saleshub.data.ProductDao
import com.example.saleshub.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {

    fun getProductsById(id: Int): Flow<List<Product>> {
        return productDao.getProductsById(id.toString())
    }


    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun deleteProduct(id: Int) {
        productDao.deleteProduct(id)
    }
}
