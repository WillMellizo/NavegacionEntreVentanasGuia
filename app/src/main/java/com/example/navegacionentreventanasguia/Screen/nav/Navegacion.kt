package com.example.navegacionentreventanasguia.Screen.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacionentreventanasguia.ContactViewModel

@Composable
fun NavigationExample(contactViewModel: ContactViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController, contactViewModel)
        }
        composable("screen_b") {
            ScreenB(navController, contactViewModel)
        }
    }
}
