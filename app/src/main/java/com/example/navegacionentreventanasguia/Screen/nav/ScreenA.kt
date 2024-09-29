package com.example.navegacionentreventanasguia.Screen.nav

import android.app.AlertDialog
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.navegacionentreventanasguia.Contact
import com.example.navegacionentreventanasguia.ContactViewModel
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.res.painterResource
import com.example.navegacionentreventanasguia.R

@Composable
fun ScreenA(navController: NavController, contactViewModel: ContactViewModel) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf("") }

    // Diálogo para registrar contacto
    val context = LocalContext.current as ComponentActivity
    var showDialog by remember { mutableStateOf(false) }

    // Fondo de pantalla con degradado gris claro
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE0E0E0), Color(0xFFB0B0B0)), // Degradado de gris claro a gris oscuro
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagen de contacto en la parte superior
            Image(
                painter = painterResource(id = R.drawable.paisaje), // Reemplaza con tu imagen de contacto
                contentDescription = "Imagen de Contacto",
                modifier = Modifier
                    .size(100.dp) // Ajusta el tamaño según sea necesario
                    .padding(bottom = 16.dp) // Espaciado inferior
            )

            Text("Agenda de Contactos", fontSize = 24.sp, color = Color.Black)

            Spacer(modifier = Modifier.height(16.dp))

            // Agrupar botones en una sola columna y centrarlos
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = { showDialog = true }) {
                    Text("Registrar Contacto")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { navController.navigate("screen_b") }) {
                    Text("Consulta")
                }
            }

            // Mostrar el diálogo si se activa
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Registrar Contacto") },
                    text = {
                        Column {
                            TextField(
                                value = nombre,
                                onValueChange = { nombre = it },
                                label = { Text("Nombre") }
                            )
                            TextField(
                                value = apellido,
                                onValueChange = { apellido = it },
                                label = { Text("Apellido") }
                            )
                            TextField(
                                value = telefono,
                                onValueChange = { input ->
                                    if (input.all { char -> char.isDigit() } || input.isEmpty()) {
                                        telefono = input
                                        phoneError = "" // Limpiar el mensaje de error si es válido
                                    } else {
                                        phoneError = "Solo se permiten números." // Mensaje de error
                                    }
                                },
                                label = { Text("Teléfono") },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                            )
                            if (phoneError.isNotEmpty()) {
                                Text(phoneError, color = Color.Red) // Mostrar el mensaje de error
                            }
                            TextField(
                                value = direccion,
                                onValueChange = { direccion = it },
                                label = { Text("Dirección") }
                            )
                        }
                    },
                    confirmButton = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = {
                                if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
                                    message = "Se deben llenar todos los campos correspondientes."
                                } else {
                                    contactViewModel.addContact(Contact(nombre, apellido, telefono, direccion))
                                    nombre = ""
                                    apellido = ""
                                    telefono = ""
                                    direccion = ""
                                    message = ""
                                    phoneError = "" // Limpiar el mensaje de error
                                    showDialog = false
                                }
                            }) {
                                Text("Registrar")
                            }

                            Button(onClick = { showDialog = false }) {
                                Text("Cancelar")
                            }
                        }

                        // Mostrar el mensaje de error debajo de los botones
                        if (message.isNotEmpty()) {
                            Text(message, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                        }
                    }
                )
            }
        }
    }
}
