package com.example.saleshub.views.home

import DeleteInventoryScreen
import EditProductScreen
import InventoryModuleScreen
import UpdateStockScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.saleshub.model.Product
import com.example.saleshub.model.Screen
import com.example.saleshub.viewmodel.ProductViewModel
import com.example.saleshub.viewmodel.ClientViewModel
import com.example.saleshub.views.accountsmodule.ViewAccountsScreenContent
import com.example.saleshub.views.accountsmodule.DeadlinesScreen
import com.example.saleshub.views.accountsmodule.DeptPaymentScreen
import com.example.saleshub.views.accountsmodule.RegisterClientScreen
import com.example.saleshub.views.inventorymodule.RegisterProductScreen
import com.example.saleshub.views.inventorymodule.ViewInventoryScreenContent
import com.example.saleshub.views.salesmodule.SalesHistoryScreen
import com.example.saleshub.views.salesmodule.SalesModuleScreen
import com.example.saleshub.views.salesmodule.registerSaleScreen



@Composable
fun MainNavGraph(navController: NavHostController, productViewModel: ProductViewModel, clientViewModel: ClientViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        //Home
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        //Modulos
        composable(Screen.SalesModule.route) {
            SalesModuleScreen(navController)
        }
        composable(Screen.AccountsModule.route) {
            ViewAccountsScreenContent(navController, clientViewModel)
        }
        composable(Screen.InventoryModule.route) {
            InventoryModuleScreen(navController)
        }

        //Pantallas secundarias de Modulo ventas
        composable(Screen.RegisterSale.route) {
            registerSaleScreen(navController)
        }
        composable(Screen.SalesHistory.route) {
            SalesHistoryScreen(navController)
        }

        //Pantallas secundarias de Modulo cuentas

        composable(Screen.Deadlines.route) {
            DeadlinesScreen(navController)
        }
        composable(Screen.DeptPayment.route) {
            DeptPaymentScreen(navController)
        }
        composable(Screen.RegisterClient.route) {
            RegisterClientScreen(navController, clientViewModel)
        }

        //Pantallas secundarios de Modulo inventario

        composable(Screen.DeleteProduct.route) {
            DeleteInventoryScreen(navController, productViewModel)
        }
        composable("edit_product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            EditProductScreen(navController, productViewModel, productId = productId, isFromSwipe = true)
        }
        composable(Screen.EditProduct.route) {
            EditProductScreen(navController, productViewModel)
        }

        composable(Screen.RegisterProduct.route) {
            RegisterProductScreen(navController, productViewModel)
        }

        composable("update_stock/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            productId?.let {
                UpdateStockScreen(navController, productViewModel, it)
            }
        }



        composable(Screen.viewInventoryContent.route) {
            ViewInventoryScreenContent(navController, productViewModel )
        }
    }
}
