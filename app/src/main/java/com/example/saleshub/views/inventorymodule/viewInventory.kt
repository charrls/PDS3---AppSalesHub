package com.example.saleshub.views.inventorymodule

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
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
fun ViewInventoryScreen(navController: NavController, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ) {

        Column (horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderViewInventory(navController, Modifier.fillMaxWidth())
            iconInventory()
            Column (modifier = Modifier
                .height(600.dp))
            {
                ViewInventory(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp))
            }

        }

    }
}





@Composable
fun HeaderViewInventory(navController: NavController, modifier: Modifier = Modifier) {

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
            text = "Ver inventario",
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
fun ViewInventory(modifier: Modifier = Modifier) {
    val stock = 5
    val minimumStock = 12
    LazyColumn(

        modifier = modifier
            .padding(horizontal = 18.dp)
            .padding(top = 10.dp)

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

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ){
                        Text("Producto:", fontWeight = FontWeight.Normal)
                        if (stock < minimumStock) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Stock bajo",
                                tint = Color.Red
                            )
                        }
                    }
                    Text("Descripción")
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    )
                    {
                        Text("Stock minimo: 12", fontWeight = FontWeight.Normal)
                        Text("Stock: 5", fontWeight = FontWeight.Bold)

                    }
                }
            }
        }
    }
}



@Preview
@Composable
private fun saleshistoryprev() {
    ViewInventoryScreen(rememberNavController())
}