package com.example.navegacionentreventanasguia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.navegacionentreventanasguia.Screen.nav.NavigationExample

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val contactViewModel: ContactViewModel = viewModel() // Obtener el ViewModel
            NavigationExample(contactViewModel) // Pasar el ViewModel a la navegación
        }
    }
}

