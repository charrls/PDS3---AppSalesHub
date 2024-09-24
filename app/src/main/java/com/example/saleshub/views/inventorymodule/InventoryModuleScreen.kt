import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.R
import com.example.saleshub.model.Screen





@Composable
fun InventoryModuleScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            encabezadoModuloInventario(navController, Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(30.dp))

            contenidoModuloInventario(navController)
        }
        pieBotonesInventario(navController)
    }
}

@Composable
fun encabezadoModuloInventario(navController: NavController, modifier: Modifier = Modifier) {
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
            text = "Modulo inventario",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}


@Composable
fun contenidoModuloInventario(navController: NavController, modifier: Modifier = Modifier) {
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

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { navController.navigate(Screen.ViewInventory.route) },
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
                .border(0.5.dp, Color.Gray, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_buttons))
        ) {
            Text(
                text = "Ver inventario",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
        }

        Button(
            onClick = { navController.navigate(Screen.UpdateStock.route) },
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
                .border(0.5.dp, Color.Gray, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_buttons))
        ) {
            Text(
                text = "Actualizar stock",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { navController.navigate(Screen.RegisterProduct.route) },
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
                .border(0.5.dp, Color.Gray, RoundedCornerShape(12.dp)),
            shape =
            RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_buttons))
        ) {
            Text(
                text = "Registrar producto",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
        }
        Button(
            onClick = { navController.navigate(Screen.EditProduct.route) },
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
                .border(0.5.dp, Color.Gray, RoundedCornerShape(12.dp)),
            shape =
            RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_buttons))
        ) {
            Text(
                text = "Editar producto",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
        }
        Button(
            onClick = { navController.navigate(Screen.DeleteProduct.route) },
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
                .border(0.5.dp, Color.Gray, RoundedCornerShape(12.dp)),
            shape =
            RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_buttons))
        ) {
            Text(
                text = "Eliminar producto",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )
        }
    }
}


@Composable
fun pieBotonesInventario(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                colorResource(id = R.color.light_gris),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomPieButtonInventario(
            text = "Inventario",
            iconResId = R.drawable.inventario,
            onClick = {  }
        )
        CustomPieButtonInventario(
            text = "Ventas",
            iconResId = R.drawable.ventas,
            onClick = { navController.navigate(Screen.SalesModule.route) }
        )
        CustomPieButtonInventario(
            text = "Cuentas",
            iconResId = R.drawable.cuentas,
            onClick = { navController.navigate(Screen.AccountsModule.route) }
        )
    }
}


@Composable
fun CustomPieButtonInventario(
    text: String,
    iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(110.dp, 70.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
        }
    }
}


@Preview
@Composable
private fun inventoryscreenprev() {
    InventoryModuleScreen(rememberNavController())
}