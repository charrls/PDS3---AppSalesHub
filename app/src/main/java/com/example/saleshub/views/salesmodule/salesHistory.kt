package com.example.saleshub.views.salesmodule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.saleshub.model.Sale
import com.example.saleshub.viewmodel.SalesViewModel
import com.example.saleshub.views.inventorymodule.ordenarProductos
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SalesHistoryScreen(navController: NavController, salesViewModel: SalesViewModel) {
    val filteredSales by salesViewModel.filteredSalesListState.collectAsState()

    var selectedFilter by remember { mutableStateOf("Todo") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderHistory(navController, Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(30.dp))
            salesIconH()
            // Mostrar botones de filtrado
            ordenarProductosSale(selectedFilter) { filter ->
                selectedFilter = filter
                salesViewModel.filterSalesByDate(filter) // Filtra las ventas según el período
            }

            // Mostrar historial de ventas
            Column(modifier = Modifier.height(500.dp)) {
                ViewHistory(filteredSales)
            }
        }

        TotalSales(filteredSales, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ViewHistory(sales: List<Sale>) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .padding(top = 10.dp)
    ) {
        items(sales) { sale ->
            // Agrupar los productos por nombre para contar cuántos se han agregado en la venta
            val productCount = sale.productos.groupBy { it }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                border = BorderStroke(0.5.dp, Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                    // Primer fila: Productos y Fecha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Productos", fontWeight = FontWeight.Bold)
                        Text("Fecha: ${formatDate(sale.fecha)}") // Formateamos la fecha
                    }

                    // Lista de productos
                    Column(modifier = Modifier.padding(top = 8.dp)) {
                        productCount.keys.forEach { productName ->
                            val count = productCount[productName]?.size ?: 0
                            val totalPrice = sale.precioTotal * count / sale.productos.size

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "$count x $productName",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                Row {
                                    Text(
                                        text = "$${"%.2f".format(totalPrice)}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black
                                    )
                                }
                            }
                            Divider()
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre filas

                    // Segunda fila: Cliente y Total
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Cliente: ${sale.idCliente ?: "N/A"}")
                        Text("Total: $${"%.2f".format(sale.precioTotal)}", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

// Función para formatear la fecha en un formato legible
fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}



@Composable
fun TotalSales(sales: List<Sale>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp)
            .padding(bottom = 18.dp),
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
            .background(
                colorResource(id = R.color.light_gris),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            )
            .padding(top = 48.dp),
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
            text = "Registrar ventas",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(end = 16.dp)
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
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.Start)
        {
            Image(
                painter = painterResource(id = R.drawable.ventas),
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
            )
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp)
                    .height(1.dp)
                    .align(alignment = Alignment.CenterVertically)
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
            FilterButton("Todo", selectedFilter, onFilterSelected)
            Spacer(modifier = Modifier.width(8.dp))
            // Botón "Adicional"
            FilterButton("Adicional", selectedFilter, onFilterSelected)
            Spacer(modifier = Modifier.width(8.dp))
            // Botón "Alimento"
            FilterButton("Alimento", selectedFilter, onFilterSelected)
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

