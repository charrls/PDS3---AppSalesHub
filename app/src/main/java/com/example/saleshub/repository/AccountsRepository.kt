package com.example.saleshub.repository

import com.example.saleshub.data.ClientDao
import com.example.saleshub.model.Client
import kotlinx.coroutines.flow.Flow

class ClientRepository(private val clientDao: ClientDao) {

    fun getAllClients(): Flow<List<Client>> = clientDao.getAllClients()

    fun getClientById(id: Int): Flow<Client> = clientDao.getClientById(id)

    suspend fun insertClient(client: Client) {
        clientDao.insertClient(client)
    }

    suspend fun deleteClient(id: Int) {
        clientDao.deleteClient(id)
    }

    suspend fun updateClient(client: Client) {
        clientDao.updateClient(client)
    }

    suspend fun updateBalance(clientId: Int, newBalance: Double) {
        clientDao.updateBalance(clientId, newBalance)
    }

    suspend fun updateTermMax(clientId: Int, newTermMax: Int) {
        clientDao.updateTermMax(clientId, newTermMax)
    }
}
