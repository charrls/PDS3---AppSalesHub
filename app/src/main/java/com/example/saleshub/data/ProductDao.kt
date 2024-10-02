package com.example.saleshub.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.saleshub.model.Product

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM product_table WHERE id = :id")
    fun getProductsById(id: String): Flow<List<Product>>

    @Query("DELETE FROM product_table WHERE id = :id")
    suspend fun deleteProduct(id: Int)
}
