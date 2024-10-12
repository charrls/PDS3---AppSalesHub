package com.example.saleshub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.data.ProductDatabase
import com.example.saleshub.repository.ProductRepository
import com.example.saleshub.ui.theme.SalesHubTheme
import com.example.saleshub.viewmodel.ProductViewModel
import com.example.saleshub.viewmodel.ProductViewModelFactory
import com.example.saleshub.views.home.MainNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SalesHubTheme {

                val navController = rememberNavController()
                val productDao = ProductDatabase.getDatabase(applicationContext).productDao()
                Log.d("MainActivity", "ProductDao initialized: $productDao")
                val repository = ProductRepository(productDao)
                val productViewModel: ProductViewModel = ViewModelProvider(
                    this,
                    ProductViewModelFactory(repository)
                ).get(ProductViewModel::class.java)
                Log.d("MainActivity", "ProductViewModel initialized: $productViewModel")

                MainNavGraph(navController, productViewModel)
            }
        }
    }
}
