package com.example.saleshub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saleshub.model.Sale
import com.example.saleshub.repository.SalesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class SalesViewModel(private val repository: SalesRepository) : ViewModel() {

    private val _salesListState = MutableStateFlow<List<Sale>>(emptyList())
    val salesListState: StateFlow<List<Sale>> = _salesListState

    private val _filteredSalesListState = MutableStateFlow<List<Sale>>(emptyList())
    val filteredSalesListState: StateFlow<List<Sale>> = _filteredSalesListState

    init {
        getAllSales()
    }

    private fun getAllSales() {
        viewModelScope.launch {
            repository.getAllSales().collect { sales ->
                _salesListState.value = sales
                _filteredSalesListState.value = sales // Al principio, mostramos todas las ventas
            }
        }
    }

    fun registerSale(
        productos: List<String>,
        cantidades: List<Int>,
        precioTotal: Double,
        esFiada: Boolean? = false,
        idCliente: Int? = null
    ) {
        val esFiadaFinal = esFiada ?: false

        val nuevaVenta = Sale(
            productos = productos,
            cantidades = cantidades,
            precioTotal = precioTotal,
            fecha = System.currentTimeMillis(),
            idCliente = idCliente,
            esFiada = esFiadaFinal
        )

        viewModelScope.launch {
            repository.insertSale(nuevaVenta)
        }
    }

    fun filterSalesByDate(period: String) {
        val filteredSales = when (period) {
            "Día" -> _salesListState.value.filter { isSameDay(it.fecha) }
            "Semana" -> _salesListState.value.filter { isSameWeek(it.fecha) }
            "Quincena" -> _salesListState.value.filter { isSameBiweek(it.fecha) }
            else -> _salesListState.value
        }
        _filteredSalesListState.value = filteredSales
    }

    private fun isSameDay(dateMillis: Long): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val today = calendar.get(Calendar.DAY_OF_YEAR)

        calendar.timeInMillis = dateMillis
        val saleDay = calendar.get(Calendar.DAY_OF_YEAR)

        return today == saleDay
    }

    private fun isSameWeek(dateMillis: Long): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val thisWeek = calendar.get(Calendar.WEEK_OF_YEAR)

        calendar.timeInMillis = dateMillis
        val saleWeek = calendar.get(Calendar.WEEK_OF_YEAR)

        return thisWeek == saleWeek
    }

    private fun isSameBiweek(dateMillis: Long): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val biweekStart = if (dayOfYear <= 15) 1 else 16

        calendar.timeInMillis = dateMillis
        val saleDayOfYear = calendar.get(Calendar.DAY_OF_YEAR)

        return saleDayOfYear in biweekStart..(biweekStart + 14)
    }
}
