package com.example.saleshub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.data.AppDatabase
import com.example.saleshub.repository.ProductRepository
import com.example.saleshub.repository.ClientRepository
import com.example.saleshub.ui.theme.SalesHubTheme
import com.example.saleshub.viewmodel.ProductViewModel
import com.example.saleshub.viewmodel.ClientViewModel
import com.example.saleshub.viewmodel.ProductViewModelFactory
import com.example.saleshub.viewmodel.ClientViewModelFactory
import com.example.saleshub.views.home.MainNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SalesHubTheme {

                val navController = rememberNavController()

                // Configuración de ProductViewModel
                val productDao = AppDatabase.getDatabase(applicationContext).productDao()
                val productRepository = ProductRepository(productDao)
                val productViewModel: ProductViewModel = ViewModelProvider(
                    this,
                    ProductViewModelFactory(productRepository)
                )[ProductViewModel::class.java]

                Log.d("MainActivity", "ProductViewModel initialized: $productViewModel")

                // Configuración de ClientViewModel
                val clientDao = AppDatabase.getDatabase(applicationContext).clientDao()
                val clientRepository = ClientRepository(clientDao)
                val clientViewModel: ClientViewModel = ViewModelProvider(
                    this,
                    ClientViewModelFactory(clientRepository)
                )[ClientViewModel::class.java]

                Log.d("MainActivity", "ClientViewModel initialized: $clientViewModel")

                // Pasar ambos ViewModels al gráfico de navegación
                MainNavGraph(
                    navController = navController,
                    productViewModel = productViewModel,
                    clientViewModel = clientViewModel
                )
            }
        }
    }
}
