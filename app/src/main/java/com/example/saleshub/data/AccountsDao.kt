package com.example.saleshub.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.saleshub.model.Client

@Dao
interface ClientDao {

    @Insert
    suspend fun insertClient(client: Client)

    @Query("SELECT * FROM client_table")
    fun getAllClients(): Flow<List<Client>>

    @Query("SELECT * FROM client_table WHERE id = :id")
    fun getClientById(id: Int): Flow<Client>

    @Query("DELETE FROM client_table WHERE id = :id")
    suspend fun deleteClient(id: Int)

    @Update
    suspend fun updateClient(client: Client)

    // Actualizar saldo del cliente
    @Query("UPDATE client_table SET debtPayment = :newBalance WHERE id = :clientId")
    suspend fun updateBalance(clientId: Int, newBalance: Double)

    // Actualizar plazo máximo permitido del cliente
    @Query("UPDATE client_table SET debtPayment = :newTermMax WHERE id = :clientId")
    suspend fun updateTermMax(clientId: Int, newTermMax: Int)
}
