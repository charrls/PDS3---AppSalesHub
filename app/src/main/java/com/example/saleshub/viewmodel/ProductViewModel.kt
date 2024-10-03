package com.example.saleshub.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saleshub.data.ProductDao
import com.example.saleshub.model.Product
import com.example.saleshub.repository.ProductRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private var productName: String = ""
    private var productDescription: String = ""
    private var productPrice: Double = 0.0
    private var productStock: Int? = null
    private var productStockmin: Int = 0
    private var productType: String = ""



    // Estado que almacena la lista de productos
    private val _productListState = MutableStateFlow<List<Product>>(emptyList())
    val productListState: StateFlow<List<Product>> = _productListState

    init {
        // Llamamos a la función para obtener todos los productos al inicializar el ViewModel
        getAllProducts()
    }

    // Función para recoger los productos desde el repositorio
    private fun getAllProducts() {
        viewModelScope.launch {
            repository.getAllProducts().collect { products ->
                _productListState.value = products // Actualiza el estado
            }
        }
    }




    fun updateProductFields(name: String, description: String, price: Double, stock: Int?, stockmin: Int, type: String) {
        productName = name
        productDescription = description
        productPrice = price
        productStock = stock
        productStockmin = stockmin
        productType = type
    }


     fun registerProduct() {
            viewModelScope.launch {
                try {
                    val newProduct = Product(
                        name = productName,
                        description = productDescription,
                        price = productPrice,
                        stock = productStock,
                        stockmin = productStockmin,
                        type = productType
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


