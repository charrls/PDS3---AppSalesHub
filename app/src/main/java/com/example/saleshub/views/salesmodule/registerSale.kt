package com.example.saleshub.views.salesmodule

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.saleshub.R
import com.example.saleshub.models.Screen

@Composable
fun registerSaleScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            salesHeader(navController, Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(30.dp))
            salesIcon()

            selectProduct()
            clientSale(navController)
            viewProducts()
            Spacer(modifier = Modifier.height(16.dp))
            SalesButtons()

        }
    }
}


@Composable
fun salesHeader(navController: NavController, modifier: Modifier = Modifier) {
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
fun salesIcon(modifier: Modifier = Modifier) {
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
fun selectProduct(modifier: Modifier = Modifier) {
    val productos = listOf("Comida 1", "Comida 2", "Comida 3", "Comida 4", "Comida 5", "Comida 6")
    val adicionales = listOf("Adicional 1", "Adicional 2", "Adicional 3", "Adicional 4", "Adicional 5", "Adicional 6")

    Column (
        modifier = Modifier.padding(22.dp)
    ){
        Text(text = "Alimentos", Modifier.padding(6.dp))
        LazyRow {
            items(productos.size) { index ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .border(0.2.dp, color = Color.Gray, RoundedCornerShape(8.dp))
                        .clickable { },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.light_buttons)),


                ) {
                    Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.Center)
                ){
                        Text(
                        text = productos[index],
                        modifier = Modifier.padding(16.dp),
                        color = Color.DarkGray
                    )}

                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = "Adicionales", Modifier.padding(6.dp))
        LazyRow {
            items(adicionales.size) { index ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .border(0.2.dp, color = Color.Gray, RoundedCornerShape(8.dp))
                        .clickable { },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.light_buttons))
                ) {
                    Text(
                        text = adicionales[index],
                        modifier = Modifier.padding(16.dp),
                        color = Color.DarkGray
                    )
                }
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.End
        ){
            Button(onClick = {},
                modifier = Modifier
                    .width(40.dp)
                    .height(38.dp)
                    .border(0.dp, Color.Transparent, RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.grayButton)),
                contentPadding = PaddingValues(0.dp)
            ){
                Icon(Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    modifier = Modifier.size(23.dp),
                    tint = Color.White
                    )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {},
                modifier = Modifier
                    .width(40.dp)
                    .height(38.dp)
                    .border(0.dp, Color.Transparent, RoundedCornerShape(10.dp)),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.greenButton)),
                contentPadding = PaddingValues(0.dp)
            ){
                    Icon(Icons.Default.AddCircle,
                        contentDescription = "Agregar",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
            }


        }
        
    }
}
@Composable
fun clientSale(navController: NavController, modifier: Modifier = Modifier) {
    val clientes = listOf("Registrar nuevo cliente", "Cliente 1", "Cliente 2", "Cliente 3", "Cliente 4", "Cliente 5", "Cliente 6")
    var isChecked by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var selectedClient by remember { mutableStateOf("Seleccionar cliente") }

    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Fiado")
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.DarkGray,
                        uncheckedColor = Color.DarkGray,
                        checkmarkColor = Color.White
                    )
                )
            }

            Box {
                OutlinedButton(
                    onClick = {
                        if (isChecked) {
                            expanded = !expanded
                        }
                    }
                ) {
                    Text(
                        selectedClient,
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
                    clientes.forEach { cliente ->
                        DropdownMenuItem(
                            modifier = Modifier.background(Color.White),
                            text = {
                                Text(
                                    text = cliente,
                                    fontSize = 14.sp, // Tamaño de fuente personalizado
                                    color = Color.DarkGray, // Color del texto
                                    modifier = Modifier.padding(8.dp) // Padding interno
                                )
                            },
                            onClick = {
                                selectedClient = cliente
                                expanded = false
                                if (cliente == "Registrar nuevo cliente") {
                                    navController.navigate(Screen.RegisterClient.route)
                                }
                            },
                        )
                    }
                }
            }
        }

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
        )
    }
}




@Composable
fun viewProducts(modifier: Modifier = Modifier) {
    val productos = listOf(
        Pair("x1 - Comida 2", "$0.0"),
        Pair("x2 - Adicional 1", "$0.0"),
        Pair("x1 - Adicional 3", "$0.0")
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .border(0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
            color = Color.White,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Productos",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.DarkGray

                )

                LazyColumn {
                    items(productos) { producto ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = producto.first,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                            Text(
                                text = producto.second,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                        Divider()
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$0.0",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Composable
fun SalesButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
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
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.greenButton)),
            modifier = Modifier
                .height(55.dp)
                .weight(1f)
                .padding(end = 10.dp)
                .border(0.dp, Color.Transparent, RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("Registrar venta", color = Color.White)
        }
    }
}


@Preview
@Composable
private fun salesmoduleprev() {
    registerSaleScreen(rememberNavController())
}