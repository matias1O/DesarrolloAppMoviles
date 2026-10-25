package com.duoc.TecnicoQCC.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()

    // Se define el navhost en este caso Login
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            // Se da la instruccion de ruta al hacer click
            QCCLoginScreenSimple(
                onLoginSuccess = {
                    navController.navigate("inicio")
                }
            )
        }
        composable("inicio") {
            InicioScreen()
        }
    }
}