package com.example.saleshub.views.salesmodule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.R
import com.example.saleshub.model.Client
import com.example.saleshub.model.Sale
import com.example.saleshub.viewmodel.ClientViewModel
import com.example.saleshub.viewmodel.SalesViewModel
import com.example.saleshub.views.inventorymodule.ordenarProductos
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SalesHistoryScreen(navController: NavController, salesViewModel: SalesViewModel, clientViewModel: ClientViewModel) {
    val filteredSales by salesViewModel.filteredSalesListState.collectAsState()

    var selectedFilter by remember { mutableStateOf("Todo") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderHistory(navController, Modifier.fillMaxWidth())
            Divider(modifier = Modifier.padding(0.dp), thickness = 1.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(30.dp))
            salesIconH()
            // Mostrar botones de filtrado
            ordenarProductosSale(selectedFilter) { filter ->
                selectedFilter = filter
                salesViewModel.filterSalesByDate(filter) // Filtra las ventas según el período
            }

            // Mostrar historial de ventas
            Column(modifier = Modifier.height(500.dp)) {
                ViewHistory(filteredSales, clientViewModel)
            }
        }
        TotalSales(filteredSales, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ViewHistory(sales: List<Sale>, clientViewModel: ClientViewModel) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 32.dp)
    ) {
        items(sales) { sale ->
            // Agrupar los productos por nombre para contar cuántos se han agregado en la venta
            val productCount = sale.productos.groupBy { it }
            val maxVisibleProducts = 2 // Máximo de productos visibles inicialmente
            var isExpanded by remember { mutableStateOf(false) } // Estado para expandir/ver más productos

            // State para el cliente, usando remember para que se guarde por venta
            var clientState by remember { mutableStateOf<Client?>(null) }

            // Obtener nombre del cliente si existe el idCliente
            LaunchedEffect(sale.idCliente) {
                if (sale.idCliente != null) {
                    clientViewModel.getClientById(sale.idCliente)
                    // Recolectamos el estado del cliente
                    clientViewModel.selectedClientState.collect { client ->
                        clientState = client
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                border = BorderStroke(0.5.dp, Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.light_gris))
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                    // Primera fila: Productos y Fecha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Productos",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${formatDate(sale.fecha)}",
                            fontSize = 12.sp
                        ) // Formateamos la fecha
                    }

                    // Lista de productos
                    Column(modifier = Modifier.padding(top = 8.dp)) {
                        val productsToShow = if (isExpanded) productCount.keys else productCount.keys.take(maxVisibleProducts)

                        productsToShow.forEach { productName ->
                            val count = productCount[productName]?.size ?: 0
                            val totalPrice = sale.precioTotal * count / sale.productos.size

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "$count x $productName",
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "$${"%.2f".format(totalPrice)}",
                                    fontSize = 12.sp,
                                    color = Color.Black
                                )
                            }
                            Divider()
                        }

                        if (productCount.keys.size > maxVisibleProducts) {
                            Text(
                                text = if (isExpanded) "Ver menos" else "Ver más",
                                fontSize = 12.sp,
                                color = colorResource(id = R.color.greenButton),
                                modifier = Modifier
                                    .clickable { isExpanded = !isExpanded }
                                    .padding(top = 4.dp)
                            )
                        }
                    }

                    // Segunda fila: Cliente y Total
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Cliente: ${clientState?.name ?: "N/A"}",
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Total: $${"%.2f".format(sale.precioTotal)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}





// Función para formatear la fecha en un formato legible
fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault()) // Añadido HH:mm para hora y minutos
    return sdf.format(Date(timestamp))
}



@Composable
fun TotalSales(sales: List<Sale>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No. de ventas: ${sales.size}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Total $: ${sales.sumOf { it.precioTotal }}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }
}


@Composable
fun HeaderHistory(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(
                colorResource(id = R.color.light_gris),
            )
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = Color.DarkGray,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "Historial de ventas",
            fontSize = 18.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(end = 22.dp)
        )
    }
}



@Composable
fun salesIconH(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween // Cambiado a SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ventas),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .align(alignment = Alignment.CenterVertically) // Alinear verticalmente
            )
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(1.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .padding(end = 2.dp)
            )

        }
    }
}



@Composable
fun ordenarProductosSale(selectedFilter: String, onFilterSelected: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .padding(bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón "Todo"
            FilterButton("Hoy", selectedFilter, onFilterSelected)
            Spacer(modifier = Modifier.width(8.dp))
            // Botón "Adicional"
            FilterButton("Semana", selectedFilter, onFilterSelected)
            Spacer(modifier = Modifier.width(8.dp))
            // Botón "Alimento"
            FilterButton("Quincena", selectedFilter, onFilterSelected)
        }
    }
}

@Composable
fun FilterButton(label: String, selectedFilter: String, onFilterSelected: (String) -> Unit) {
    val isSelected = selectedFilter == label

    androidx.compose.material3.Button(
        onClick = { onFilterSelected(label) },
        modifier = Modifier
            .border(0.5.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .height(26.dp),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.Gray else colorResource(id = R.color.light_buttons)
        )
    ) {
        Column (
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            androidx.compose.material.Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = if (isSelected) Color.White else Color.DarkGray
            )
        }

    }
}

