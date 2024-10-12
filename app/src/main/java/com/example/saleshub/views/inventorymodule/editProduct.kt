import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.R
import com.example.saleshub.model.Product
import com.example.saleshub.viewmodel.ProductViewModel
import kotlinx.coroutines.launch


@Composable
fun EditProductScreen(
    navController: NavController,
    productViewModel: ProductViewModel,
    modifier: Modifier = Modifier
) {
    val productList by productViewModel.productListState.collectAsState()
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productStock by remember { mutableStateOf("") }
    var productStockMin by remember { mutableStateOf("") }
    var productType by remember { mutableStateOf("") }
    var showConfirmDialog by remember { mutableStateOf(false) } // Estado para mostrar el diálogo de confirmación

    // Estado del Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Usar Scaffold para la estructura básica
    Scaffold(
        topBar = {
            HeaderEditInventory(navController, Modifier.fillMaxWidth())
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
                    iconEInventory()

                    // Dropdown para seleccionar el producto
                    SelectEditProduct(
                        productList = productList,
                        selectedProduct = selectedProduct,
                        onProductSelected = { product ->
                            selectedProduct = product
                            productName = product.name
                            productDescription = product.description
                            productPrice = product.price.toString()
                            productStock = product.stock?.toString() ?: ""
                            productStockMin = product.stockmin.toString()
                            productType = product.type
                        }
                    )

                    // Formulario para editar el producto
                    selectedProduct?.let {
                        EditProductForm(
                            productName = productName,
                            onProductNameChange = { productName = it },
                            productDescription = productDescription,
                            onProductDescriptionChange = { productDescription = it },
                            productPrice = productPrice,
                            onProductPriceChange = { productPrice = it },
                            productStock = productStock,
                            onProductStockChange = { productStock = it },
                            productStockMin = productStockMin,
                            onProductStockMinChange = { productStockMin = it },
                            productType = productType
                        )
                    }
                }

                // Botón de actualizar
                FootUpdateButtons(
                    onUpdateClicked = {
                        // Muestra el diálogo de confirmación al hacer clic en "Actualizar"
                        showConfirmDialog = true
                    }
                )
            }
        }
    )

    // AlertDialog de confirmación para actualizar el producto
    if (showConfirmDialog) {
        ConfirmUpdateDialog(
            productName = productName,
            onConfirmUpdate = {
                selectedProduct?.let { product ->
                    productViewModel.updateProduct(
                        Product(
                            id = product.id,
                            name = productName,
                            description = productDescription,
                            price = productPrice.toDoubleOrNull() ?: 0.0,
                            stock = productStock.toIntOrNull(),
                            stockmin = productStockMin.toIntOrNull() ?: 0,
                            type = productType
                        )
                    )


                    // Mostrar el Snackbar al actualizar el producto
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Producto actualizado con éxito",
                            duration = SnackbarDuration.Short
                        )
                    }


                }
                showConfirmDialog = false
            },
            onDismiss = { showConfirmDialog = false }
        )
    }
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
fun SelectEditProduct(
    productList: List<Product>,
    selectedProduct: Product?,
    onProductSelected: (Product) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedProductName by remember { mutableStateOf(selectedProduct?.let { "${it.name}: ${it.description}" } ?: "Seleccionar producto") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .padding(horizontal = 30.dp)
    ) {
        Box {
            OutlinedButton(
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .width(250.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedProductName,
                        fontSize = 16.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Desplegar",
                        tint = Color.DarkGray
                    )
                }
            }
            DropdownMenu(
                modifier = Modifier
                    .background(Color.White)
                    .height(400.dp)
                    .width(250.dp),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                productList.forEach { product ->
                    DropdownMenuItem(
                        modifier = Modifier.background(Color.White),
                        onClick = {
                            selectedProductName = "${product.name}: ${product.description}"
                            expanded = false
                            onProductSelected(product)
                        },
                        text = {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = "${product.name}: ${product.description}",
                                    fontSize = 14.sp,
                                    color = Color.DarkGray,
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = "${product.type}",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(26.dp))
    }
}




@Composable
fun EditProductForm(
    productName: String,
    onProductNameChange: (String) -> Unit,
    productDescription: String,
    onProductDescriptionChange: (String) -> Unit,
    productPrice: String,
    onProductPriceChange: (String) -> Unit,
    productStock: String,
    onProductStockChange: (String) -> Unit,
    productStockMin: String,
    onProductStockMinChange: (String) -> Unit,
    productType: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 40.dp)
    ) {
        Text(text = "Producto")
        OutlinedTextField(
            value = productName,
            onValueChange = onProductNameChange,
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))

        Text(text = "Descripción")
        OutlinedTextField(
            value = productDescription,
            onValueChange = onProductDescriptionChange,
            label = { Text("Descripción del producto") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))

        // Mostrar stock mínimo solo si el tipo de producto es "Adicional"
        if (productType == "Adicional") {
            Text(text = "Stock mínimo")
            OutlinedTextField(
                value = productStockMin,
                onValueChange = onProductStockMinChange,
                label = { Text("Stock mínimo") },
                modifier = Modifier.width(150.dp),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(25.dp))
        }

        Text(text = "Precio")
        OutlinedTextField(
            value = productPrice,
            onValueChange = onProductPriceChange,
            label = { Text("$ 0.0") },
            modifier = Modifier.width(150.dp),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )
    }
}




@Composable
fun FootUpdateButtons(
    onUpdateClicked: () -> Unit,
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
            onClick = { /* Cancelar acción */ },
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
            onClick = onUpdateClicked,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orangeButton)),
            modifier = Modifier
                .height(55.dp)
                .weight(1f)
                .padding(end = 10.dp)
                .border(0.dp, Color.Transparent, RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("Actualizar", color = Color.White)
        }
    }
}



@Composable
fun HeaderEditInventory(navController: NavController, modifier: Modifier = Modifier) {

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
            text = "Editar producto",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(end = 16.dp)
        )
    }

}

@Composable
fun iconEInventory(modifier: Modifier = Modifier) {
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
fun ConfirmUpdateDialog(
    productName: String,
    onConfirmUpdate: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Actualizar producto",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "\"$productName\"",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "¿Está seguro de que desea actualizar este producto?",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            }
        },
        confirmButton = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(color = Color.LightGray, thickness = 1.dp)
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Botón de "Cancelar"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onDismiss() }
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Cancelar",
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )
                    }

                    // Línea divisoria entre los botones
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(45.dp)
                            .background(Color.LightGray)
                    )

                    // Botón de "Actualizar"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                onConfirmUpdate()
                                onDismiss()  // Cierra el diálogo después de confirmar
                            }
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Actualizar",
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.orangeButton)
                        )
                    }
                }
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(16.dp)
    )
}
