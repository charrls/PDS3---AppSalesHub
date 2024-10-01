package com.example.saleshub.views.home

import DeleteInventoryScreen
import EditProductScreen
import InventoryModuleScreen
import UpdateStockScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.saleshub.models.Screen
import com.example.saleshub.views.accountsmodule.AccountsModuleScreen
import com.example.saleshub.views.accountsmodule.DeadlinesScreen
import com.example.saleshub.views.accountsmodule.DeptPaymentScreen
import com.example.saleshub.views.accountsmodule.RegisterClientScreen
import com.example.saleshub.views.inventorymodule.RegisterProductScreen
import com.example.saleshub.views.inventorymodule.ViewInventoryScreen
import com.example.saleshub.views.salesmodule.SalesHistoryScreen
import com.example.saleshub.views.salesmodule.SalesModuleScreen
import com.example.saleshub.views.salesmodule.registerSaleScreen



@Composable
fun MainNavGraph(navController: NavHostController) {
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
            AccountsModuleScreen(navController)
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
            RegisterClientScreen(navController)
        }

        //Pantallas secundarios de Modulo inventario

        composable(Screen.DeleteProduct.route) {
            DeleteInventoryScreen(navController)
        }
        composable(Screen.EditProduct.route) {
            EditProductScreen(navController)
        }
        composable(Screen.RegisterProduct.route) {
            RegisterProductScreen(navController)
        }
        composable(Screen.UpdateStock.route) {
            UpdateStockScreen(navController)
        }
        composable(Screen.ViewInventory.route) {
            ViewInventoryScreen(navController)
        }

    }
}
