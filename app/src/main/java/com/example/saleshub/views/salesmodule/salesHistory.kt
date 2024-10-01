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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.R

@Composable
fun SalesHistoryScreen(navController: NavController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ) {

        Column (horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderHistory(navController,Modifier.fillMaxWidth())
            HistoryButtons(navController,modifier = Modifier.padding(16.dp))

            Column (modifier = Modifier
                .height(400.dp))
            {
                ViewHistory(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp))
            }

        }

        TotalSales(modifier = Modifier.padding(16.dp))
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
            text = "Registrar ventas",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}



@Composable
fun HistoryButtons(navController: NavController, modifier: Modifier = Modifier) {


    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        )
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


        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(
                onClick = {  },
                modifier = Modifier
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(12.dp))
                    .height(40.dp),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_buttons))
            ) {
                Text(
                    text = "Dia",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {  },
                modifier = Modifier
                    .border(0.5.dp, Color.LightGray, RoundedCornerShape(12.dp))
                    .height(40.dp),
                contentPadding = PaddingValues(10.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_buttons))
            ) {
                Text(
                    text = "Semana",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.light_buttons)),
                        modifier = Modifier
                            .border(0.5.dp, Color.LightGray, RoundedCornerShape(12.dp))
                            .height(40.dp),
                        contentPadding = PaddingValues(10.dp),

                ) {
                Text(
                    text = "Quincena",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray
                )
            }
        }

    }
}

@Composable
fun ViewHistory(modifier: Modifier = Modifier) {
    LazyColumn(

        modifier = modifier
            .padding(horizontal = 18.dp)
            .padding(top =10.dp)

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
                    Text("Productos: -", fontWeight = FontWeight.Bold)
                    Text("Fecha: 10/09/2024")
                    Text("Cliente (si aplica): -")
                    Text("Total: $0.0", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Composable
fun TotalSales(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp)
            .padding(bottom = 18.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center

    ) {
        Text("Total de ventas: 100",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray
)
        Spacer(modifier = Modifier.height(10.dp))

        Text("Total $: $0.0",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray)
    }
}

@Preview
@Composable
private fun saleshistoryprev() {
    SalesHistoryScreen(rememberNavController())
}