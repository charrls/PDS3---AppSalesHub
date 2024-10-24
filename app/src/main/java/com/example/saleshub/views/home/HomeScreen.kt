package com.example.saleshub.views.home

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(60.dp))
            negocio(Modifier.padding(16.dp))
            Spacer(modifier = Modifier.height(70.dp))
            accesoRapido(navController)
        }
        pieBotones(navController)
    }
}



@Composable
fun Header(modifier: Modifier = Modifier) {

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(id = R.color.light_gris),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            )
            .shadow(1.dp, shape = RoundedCornerShape(12.dp))
            .padding(top = 48.dp)



    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.store),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = "Gestor de ventas",
                fontSize = 20.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

}

@Composable
fun negocio(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .border(0.5.dp, Color.Gray, RoundedCornerShape(10.dp))
            .padding(16.dp)
            .width(280.dp)
            .height(70.dp)
    ) {
        Text(text = "Nombre del negocio", fontSize = 12.sp)
        Text(text = "Nombre del dueño", fontSize = 12.sp)
    }
}

@Composable
fun accesoRapido(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()

    ) {
        Row (   modifier = modifier
            .fillMaxWidth()
            .padding(start = 28.dp),
            horizontalArrangement = Arrangement.Start){
            Text(text = "Acceso rápido", fontSize = 12.sp)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CustomButton(
                text = "Ver inventario",
                onClick = {
                    navController.navigate(Screen.viewInventoryContent.route)
                }
            )
            CustomButton(
                text = "Registrar ventas",
                onClick = {
                    navController.navigate(Screen.RegisterSale.route)
                }
            )
            CustomButton(
                text = "Pago de deuda",
                onClick = {
                    navController.navigate(Screen.DeptPayment.route)
                }
            )
        }
    }
}


@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(104.dp, 80.dp)
            .shadow(3.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_buttons))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
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


@Composable
fun pieBotones(navController: NavController, modifier: Modifier = Modifier) {
    Column (

        modifier = Modifier
            .fillMaxWidth()
            .background(
                colorResource(id = R.color.light_gris),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)

            )
            .shadow(1.dp, shape = RoundedCornerShape(12.dp))

    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomPieButton(
                text = "Inventario",
                iconResId = R.drawable.inventario,
                onClick = {
                    navController.navigate(Screen.viewInventoryContent.route)
                }
            )
            CustomPieButton(
                text = "Ventas",
                iconResId = R.drawable.ventas,
                onClick = {
                    navController.navigate(Screen.SalesModule.route)
                }
            )
            CustomPieButton(
                text = "Cuentas",
                iconResId = R.drawable.cuentas,
                onClick = {
                    navController.navigate(Screen.AccountsModule.route)
                }
            )
        }
    }

}

@Composable
fun CustomPieButton(
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
private fun homeScreenPrev() {
    HomeScreen(rememberNavController())
}