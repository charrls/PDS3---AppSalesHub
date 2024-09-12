package com.example.saleshub.ui.theme.accountsmodule

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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
fun DeptPaymentScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderPaymentClient(navController, Modifier.fillMaxWidth())
            iconDPayment()
            Column (modifier = Modifier
                .height(220.dp))
            {
                ViewClients(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp))
            }
            ClientDetails()
            PaymentForm()
        }
        FootDPaymentBottons()
    }
}


@Composable
fun HeaderPaymentClient(navController: NavController, modifier: Modifier = Modifier) {

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
            text = "Pago de deuda",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(end = 16.dp)
        )
    }

}

@Composable
fun iconDPayment(modifier: Modifier = Modifier) {
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
                painter = painterResource(id = R.drawable.cuentas),
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
fun ViewClients(modifier: Modifier = Modifier) {

    LazyColumn(

        modifier = modifier
            .padding(horizontal = 18.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(16.dp)

    ) {
        items(10) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                border = BorderStroke(0.5.dp, Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = Color.White)

            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Nombre: cliente", fontWeight = FontWeight.Normal)
                }
            }
        }
    }
}


@Composable
fun ClientDetails(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.padding(horizontal = 40.dp, vertical = 20.dp)

    ) {
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
        )
        Spacer(modifier = modifier.height(30.dp))
        Row (modifier = modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround)
        {
            Text(text = "Cliente: Nombre")
            Text(text = "Telefono: 662-0000000")

        }
        Spacer(modifier = modifier.height(30.dp))
        Column (modifier = modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
           )
        {
            Text(text = "Deuda: $0.0",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                )

        }
        Spacer(modifier = modifier.height(30.dp))
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
        )
    }
}


@Composable
fun PaymentForm(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Pago/abono")
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("$0.0") },
            modifier = Modifier.width(150.dp),
            shape = RoundedCornerShape(8.dp)

        )

    }
}


@Composable
fun FootDPaymentBottons(modifier: Modifier = Modifier) {
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
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purpleButton)),
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
private fun deptpraymentPrev() {
    DeptPaymentScreen(rememberNavController())
}