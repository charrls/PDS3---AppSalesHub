package com.example.saleshub.views.inventorymodule

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.R
import com.example.saleshub.viewmodel.ProductViewModel

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun RegisterProductScreen(
    navController: NavController,
    productViewModel: ProductViewModel,
    modifier: Modifier = Modifier
) {
    var productType by remember { mutableStateOf("Alimento") }

    // Estado para el Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Usar Scaffold para la estructura básica
    Scaffold(
        topBar = {
            HeaderRegisterInventory(navController, Modifier.fillMaxWidth())
        },
        snackbarHost = {
            MySnackbarHost(snackbarHostState) // Usamos nuestra función personalizada
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    iconRInventory()
                    SelectTypeProduct { selectedType -> productType = selectedType }
                    ProductForm(productType = productType, productViewModel = productViewModel)
                }
                FootRegisterButtons(
                    productViewModel = productViewModel,
                    productType = productType,
                    onRegisterSuccess = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Producto registrado con éxito",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                )
            }
        }
    )
}
@Composable
fun MySnackbarHost(snackbarHostState: SnackbarHostState) {
    SnackbarHost(snackbarHostState) { snackbarData: SnackbarData ->
        Snackbar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 120.dp),
            snackbarData = snackbarData,
            shape = RoundedCornerShape(8.dp) // Forma opcional
        )
    }
}








@Composable
fun ProductForm(productType: String, productViewModel: ProductViewModel, modifier: Modifier = Modifier) {
    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productStock by remember { mutableStateOf("") }
    var productStockmin by remember { mutableStateOf("") }




    Column(
        modifier = modifier.padding(horizontal = 40.dp)
    ) {
        Text(text = "Producto")
        OutlinedTextField(
            value = productName,
            onValueChange = { productName = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = modifier.height(25.dp))
        Text(text = "Descripción")
        OutlinedTextField(
            value = productDescription,
            onValueChange = { productDescription = it },
            label = { Text("Descripción del producto") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = modifier.height(25.dp))
        if (productType == "Adicional") {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Text(text = "Stock")
                    OutlinedTextField(
                        value = productStock,
                        onValueChange = { productStock = it },
                        label = { Text("Stock inicial") },
                        modifier = Modifier.width(150.dp),
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

                    )
                }
                Column {
                    Text(text = "")
                    OutlinedTextField(
                        value = productStockmin,
                        onValueChange = { productStockmin = it },
                        label = { Text("Stock mínimo") },
                        modifier = Modifier.width(150.dp),
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
}


        }
        Spacer(modifier = modifier.height(25.dp))
        Text(text = "Precio")
        OutlinedTextField(
            value = productPrice,
            onValueChange = { productPrice = it },
            label = { Text("$ 0.0") },
            modifier = Modifier.width(150.dp),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


        Spacer(modifier = modifier.height(25.dp))
        // Guardamos los datos en el ViewModel cuando el formulario cambie
        productViewModel.updateProductFields(
            name = productName,
            description = productDescription,
            price = productPrice.toDoubleOrNull() ?: 0.0,
            stock = if (productType == "Adicional") productStock.toIntOrNull() ?: 0 else null,
            stockmin = if (productStockmin.isNotEmpty()) productStockmin.toInt() else 0, // Verifica que no esté vacío
            type = productType
        )
    }
}

@Composable
fun HeaderRegisterInventory(navController: NavController, modifier: Modifier = Modifier) {

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
            text = "Registrar producto",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(end = 16.dp)
        )
    }

}

@Composable
fun iconRInventory(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.Start)
        {
            Image(
                painter = painterResource(id = R.drawable.inventario),
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
fun SelectTypeProduct(modifier: Modifier = Modifier, onTypeSelected: (String) -> Unit) {
    val productList = listOf("Alimento", "Adicional")
    var expanded by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf(productList[0]) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .padding(horizontal = 30.dp)
    ) {
        Box {
            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(
                    selectedProduct,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .padding(end = 12.dp)
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Desplegar",
                    tint = Color.DarkGray
                )
            }
            DropdownMenu(
                modifier = Modifier.background(Color.White),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                productList.forEach { product ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .width(145.dp)
                            .background(Color.White),
                        text = {
                            Text(
                                text = product,
                                fontSize = 14.sp, // Tamaño de fuente personalizado
                                color = Color.DarkGray, // Color del texto
                                modifier = Modifier.padding(8.dp) // Padding interno
                            ) },
                        onClick = {
                            selectedProduct = product
                            expanded = false
                            onTypeSelected(product)
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(26.dp))
    }
}




@Composable
fun FootRegisterButtons(
    productViewModel: ProductViewModel,
    productType: String, // Agregar este parámetro
    onRegisterSuccess: () -> Unit, // Función para manejar el registro exitoso
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { /* Navegar hacia atrás o limpiar campos */ },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.grayButton)),
            modifier = Modifier
                .height(55.dp)
                .weight(0.7f)
                .padding(start = 10.dp)
                .border(0.dp, Color.Transparent, RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("Cancelar", color = Color.White)
        }

        Spacer(modifier = modifier.width(40.dp))
        Button(
            onClick = {
                // Llamar a la función de registrar el producto en el ViewModel
                productViewModel.registerProduct()
                // Llama a la función de éxito
                onRegisterSuccess() // Mostrar el Snackbar
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orangeButton)),
            modifier = Modifier
                .height(55.dp)
                .weight(1f)
                .padding(end = 10.dp)
                .border(0.dp, Color.Transparent, RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("Registrar", color = Color.White)
        }
    }
}




