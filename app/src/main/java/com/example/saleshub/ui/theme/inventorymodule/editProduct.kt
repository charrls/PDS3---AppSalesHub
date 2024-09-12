import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.R



@Composable
fun EditProductScreen(navController: NavController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderEditInventory(navController, Modifier.fillMaxWidth())
            iconEInventory()
            SelectEditProduct()
            EditProductForm()
        }
        FootRegisterButtons()
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
fun SelectEditProduct(modifier: Modifier = Modifier) {
    val productList = listOf("Adicional 1","Alimento3", "Adicional 2", "etc...")
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
                    modifier = Modifier.padding(end = 12.dp)
                )
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Desplegar")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                productList.forEach { product ->
                    DropdownMenuItem(
                        text = { Text(product) },
                        onClick = {
                            selectedProduct = product
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(26.dp))
    }
}


@Composable
fun EditProductForm(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 40.dp)
    ) {
        Text(text = "Producto")
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)

        )
        Spacer(modifier = modifier.height(25.dp))
        Text(text = "Descripción")
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Descripción del producto") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            /*textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )*/
        )
        Spacer(modifier = modifier.height(25.dp))
        Text(text = "Precio")
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("$ 0.0") },
            modifier = Modifier.width(150.dp),
            shape = RoundedCornerShape(8.dp)
        )

    }
}



@Composable
fun FootRegisterButtons(modifier: Modifier = Modifier) {
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

@Preview
@Composable
private fun editproductPrev() {
    EditProductScreen(rememberNavController())
}

