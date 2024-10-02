package com.example.saleshub.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saleshub.data.ProductDao
import com.example.saleshub.model.Product
import com.example.saleshub.repository.ProductRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private var productName: String = ""
    private var productDescription: String = ""
    private var productPrice: Double = 0.0
    private var productStock: Int? = null

    fun updateProductFields(name: String, description: String, price: Double, stock: Int?) {
        productName = name
        productDescription = description
        productPrice = price
        productStock = stock
    }


     fun registerProduct() {
            viewModelScope.launch {
                try {
                    val newProduct = Product(
                        name = productName,
                        description = productDescription,
                        price = productPrice,
                        stock = productStock
                    )
                    repository.insertProduct(newProduct)
                } catch (e: Exception) {
                    Log.e("ProductViewModel", "Error al registrar el producto: ${e.message}")
                }
            }
        }
/*
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    fun getProductsByType(type: String): Flow<List<Product>> {
        return repository.getProductsByType(type)
    }

    fun insertProduct(name: String, description: String, price: Double, stock: Int = 0, productType: String) {
        viewModelScope.launch {
            val product = Product(
                name = name,
                description = description,
                price = price,
                stock = stock,
            )
            repository.insertProduct(product)
        }
    }
    */

    }


