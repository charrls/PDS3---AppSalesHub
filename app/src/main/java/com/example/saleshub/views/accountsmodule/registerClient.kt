package com.example.saleshub.views.accountsmodule

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.saleshub.R
import com.example.saleshub.model.Client
import com.example.saleshub.viewmodel.ClientViewModel

@Composable
fun RegisterClientScreen(
    navController: NavController,
    accountViewModel: ClientViewModel, // Pasamos el ViewModel como parámetro
    modifier: Modifier = Modifier
) {
    // Estados para los campos
    var clientName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf("0.0") }
    var creditMax by remember { mutableStateOf("0.0") }
    var termMax by remember { mutableStateOf("0") }

    // Validación de formulario
    val isFormValid by remember(clientName, phoneNumber, balance, creditMax, termMax) {
        derivedStateOf {
            clientName.isNotBlank() &&
                    phoneNumber.isNotBlank() &&
                    balance.toDoubleOrNull() != null &&
                    creditMax.toDoubleOrNull() != null &&
                    termMax.toIntOrNull() != null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderRegisterClient(navController, Modifier.fillMaxWidth())
            iconAInventory()
            RegisterClientForm(
                clientName = clientName,
                phoneNumber = phoneNumber,
                balance = balance,
                creditMax = creditMax,
                termMax = termMax,
                onClientNameChange = { clientName = it },
                onPhoneNumberChange = { phoneNumber = it },
                onBalanceChange = { balance = it },
                onCreditMaxChange = { creditMax = it },
                onTermMaxChange = { termMax = it }
            )
        }
        FootRClientBottons(
            onCancel = { navController.popBackStack() },
            onRegister = {
                accountViewModel.registerClient(
                    name = clientName,
                    num = phoneNumber,
                    balance = balance.toDouble(),
                    creditMax = creditMax.toDouble(),
                    termMax = termMax.toInt()
                )
                navController.popBackStack()
            },
            isFormValid = isFormValid
        )
    }
}

@Composable
fun RegisterClientForm(
    clientName: String,
    phoneNumber: String,
    balance: String,
    creditMax: String,
    termMax: String,
    onClientNameChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    onBalanceChange: (String) -> Unit,
    onCreditMaxChange: (String) -> Unit,
    onTermMaxChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 40.dp, vertical = 40.dp)) {
        Text(text = "Cliente")
        OutlinedTextField(
            value = clientName,
            onValueChange = onClientNameChange,
            label = { Text("Nombre del cliente") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = modifier.height(30.dp))
        Text(text = "Teléfono")
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = { Text("Número de teléfono") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

    }
}

@Composable
fun FootRClientBottons(
    onCancel: () -> Unit,
    onRegister: () -> Unit,
    isFormValid: Boolean,
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
            onClick = onCancel,
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
            onClick = onRegister,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purpleButton)),
            modifier = Modifier
                .height(55.dp)
                .weight(1f)
                .padding(end = 10.dp)
                .border(0.dp, Color.Transparent, RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
            enabled = isFormValid
        ) {
            Text("Registrar", color = Color.White)
        }
    }
}

@Composable
fun HeaderRegisterClient(navController: NavController, modifier: Modifier = Modifier) {
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
            text = "Registrar cliente",
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Composable
fun iconAInventory(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.cuentas),
                contentDescription = null,
                modifier = Modifier.size(35.dp)
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
