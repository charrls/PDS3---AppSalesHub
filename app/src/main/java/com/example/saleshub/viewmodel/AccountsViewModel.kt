// viewmodel/ClientViewModel.kt
package com.example.saleshub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saleshub.model.Client
import com.example.saleshub.repository.ClientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.sql.Date

class ClientViewModel(private val repository: ClientRepository) : ViewModel() {

    private var clientName: String = ""
    private  var clientPhoneNumber: String = ""

    private val _clientListState = MutableStateFlow<List<Client>>(emptyList())
    val clientListState: StateFlow<List<Client>> = _clientListState

    init {
        getAllClients()
    }

    private fun getAllClients() {
        viewModelScope.launch {
            repository.getAllClients().collect { clients ->
                _clientListState.value = clients
            }
        }
    }
    fun updateClientFields(name: String, phone: String) {
        clientName = name
        clientPhoneNumber = phone

    }

    fun registerClient(name: String, num: String, balance: Double, creditMax: Double, termMax: Int) {
        val newClient = Client(
            name = name,
            phone = num,
            debtPayment = balance,
            maxAmount = creditMax,
            maxTerm = termMax,
        )
        viewModelScope.launch { repository.insertClient(newClient) }
    }

    fun updateClient(client: Client) {
        viewModelScope.launch { repository.updateClient(client) }
    }

    fun updateBalance(clientId: Int, newBalance: Double) {
        viewModelScope.launch { repository.updateBalance(clientId, newBalance) }
    }

    fun updateTermMax(clientId: Int, newTermMax: Int) {
        viewModelScope.launch { repository.updateTermMax(clientId, newTermMax) }
    }

    fun deleteClient(clientId: Int) {
        viewModelScope.launch { repository.deleteClient(clientId) }
    }
}
