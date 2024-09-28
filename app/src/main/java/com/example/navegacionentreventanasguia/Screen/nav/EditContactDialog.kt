package com.example.navegacionentreventanasguia.Screen.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.navegacionentreventanasguia.Contact
@Composable
fun EditContactDialog(contact: Contact, onDismiss: () -> Unit, onSave: (Contact) -> Unit) {
    var nombre by remember { mutableStateOf(contact.nombre) }
    var apellido by remember { mutableStateOf(contact.apellido) }
    var telefono by remember { mutableStateOf(contact.telefono) }
    var direccion by remember { mutableStateOf(contact.direccion) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Contacto") },
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
                    onValueChange = { telefono = it.filter { char -> char.isDigit() } }, // Filtrar solo números
                    label = { Text("Teléfono") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number) // Asegúrate de usar KeyboardType
                )
                TextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val updatedContact = contact.copy(nombre = nombre, apellido = apellido, telefono = telefono, direccion = direccion)
                onSave(updatedContact)
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
