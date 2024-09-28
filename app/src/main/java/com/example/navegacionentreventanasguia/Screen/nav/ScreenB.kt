package com.example.navegacionentreventanasguia.Screen.nav

import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navegacionentreventanasguia.Contact
import com.example.navegacionentreventanasguia.ContactViewModel

@Composable
fun ScreenB(navController: NavController, contactViewModel: ContactViewModel) {
    val contacts = contactViewModel.contacts // Obtener la lista de contactos del ViewModel
    val context = LocalContext.current as ComponentActivity // Contexto para cerrar la actividad

    var selectedContact by remember { mutableStateOf<Contact?>(null) } // Contacto seleccionado para editar
    var showEditDialog by remember { mutableStateOf(false) } // Estado para mostrar el diálogo de edición

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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contactos Registrados",
                fontSize = 24.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Lista de Contactos",
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            // Mostrar los contactos en una lista usando LazyColumn
            LazyColumn(
                modifier = Modifier.weight(1f) // Para que la lista ocupe el espacio restante
            ) {
                items(contacts) { contact ->
                    ContactCard(contact,
                        onEditClick = {
                            selectedContact = contact
                            showEditDialog = true
                        },
                        onDeleteClick = {
                            contactViewModel.removeContact(contact)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para volver a la pantalla anterior
            Button(onClick = {
                navController.popBackStack() // Regresa a la pantalla anterior
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Volver a la Pantalla A")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón para salir de la aplicación
            Button(
                onClick = {
                    context.finish() // Cierra la aplicación
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error) // Color rojo para indicar salir
            ) {
                Text("Salir de la Aplicación")
            }
        }

        // Mostrar el diálogo de edición si se ha seleccionado un contacto
        if (showEditDialog && selectedContact != null) {
            EditContactDialog(
                contact = selectedContact!!,
                onDismiss = { showEditDialog = false },
                onSave = { updatedContact ->
                    contactViewModel.updateContact(updatedContact)
                    showEditDialog = false
                }
            )
        }
    }
}

@Composable
fun ContactCard(contact: Contact, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Nombre: ${contact.nombre}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Apellido: ${contact.apellido}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Teléfono: ${contact.telefono}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Dirección: ${contact.direccion}", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(onClick = onEditClick) {
                    Text("Editar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDeleteClick) {
                    Text("Eliminar")
                }
            }
        }
    }
}
