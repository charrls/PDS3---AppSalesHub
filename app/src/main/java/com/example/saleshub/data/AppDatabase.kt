package com.example.saleshub.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.saleshub.model.Client
import com.example.saleshub.model.Product

@Database(entities = [Product::class, Client::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun clientDao(): ClientDao // Añadir ClientDao aquí

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "saleshub_database" // Cambiar el nombre de la base de datos si es necesario
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

