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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.R



@Composable
fun UpdateStockScreen(navController: NavController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ) {

        Column (horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderStockInventory(navController, Modifier.fillMaxWidth())
            iconInventory()
            SelectProduct()
            AddStockButton()
        }
        FootStockButtons()

    }
}




@Composable
fun HeaderStockInventory(navController: NavController, modifier: Modifier = Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(
                colorResource(id = R.color.light_gris),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            )
            .padding(16.dp)
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
            text = "Actualizar stock",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(end = 16.dp)
        )
    }

}

@Composable
fun iconInventory(modifier: Modifier = Modifier) {
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
fun SelectProduct(modifier: Modifier = Modifier) {
    val productList = listOf("Producto 1", "Producto 2", "Producto 3")
    var expanded by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf(productList[0]) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Box {
            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(
                    selectedProduct,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(end = 12.dp)
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
                        modifier = Modifier.background(Color.White),
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
                        }
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(26.dp))
        ProductDetailsCard(productName = selectedProduct)
    }
}

@Composable
fun AddStockButton(modifier: Modifier = Modifier) {
    var stockCount by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text("Añadir stock",
                modifier = Modifier.padding(end = 52.dp, bottom = 8.dp)
            )
        Row(
            verticalAlignment = Alignment.CenterVertically,


        ) {
            OutlinedTextField(
                value = stockCount.toString(),
                onValueChange = { newValue ->
                    stockCount = newValue.toIntOrNull() ?: stockCount
                },
                readOnly = true,
                singleLine = true,
                modifier = Modifier.width(80.dp)
            )
            Button(
                onClick = { stockCount++ },
                modifier = Modifier
                    .width(55.dp)
                    .height(55.dp)
                    .border(0.dp, Color.Transparent, RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                contentPadding = PaddingValues(0.dp)
                ) {
                Icon(Icons.Default.Add, contentDescription = "Incrementar")
            }
        }
    }
}


@Composable
fun FootStockButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {  },
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
            onClick = {  },
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



@Composable
fun ProductDetailsCard(productName: String, modifier: Modifier = Modifier) {
    val stock = 5
    val minimumStock = 12

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Producto: $productName")
                if (stock < minimumStock) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Stock bajo",
                        tint = Color.Red
                    )
                }
            }

                Text("Descripción: Descripción breve")
                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Stock mínimo: $minimumStock")

                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text("Stock: ",
                            fontWeight = FontWeight.Bold
                        )
                        Text("$stock",
                            color = if (stock < minimumStock) Color.Red else Color.Green,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }


                }
            }
        }
    }






@Preview
@Composable
private fun saleshistoryprev() {
    UpdateStockScreen(rememberNavController())
}