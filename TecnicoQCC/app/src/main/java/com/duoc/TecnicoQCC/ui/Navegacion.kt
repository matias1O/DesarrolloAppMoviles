package com.duoc.TecnicoQCC.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.TecnicoQCC.repository.UserPreferences
import androidx.compose.runtime.getValue

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    // Leemos el estado guardado de la sesión
    val estaLogueado by userPreferences.estaLogueado.collectAsState(initial = false)

    // Si ya está logueado, inicia directo en "inicio"
    val pantallaInicial = if (estaLogueado) "inicio" else "login"

    NavHost(navController = navController, startDestination = pantallaInicial) {
        composable("login") {
            QCCLoginScreenSimple(
                onLoginSuccess = {
                    navController.navigate("inicio") {
                        popUpTo("login") { inclusive = true } // Limpia el historial para no regresar al login
                    }
                }
            )
        }

        composable("inicio") {
            InicioScreen(
                onIrAPerfil = { navController.navigate("perfil") }
            )
        }

        composable("perfil") {
            PantallaPerfil(
                onVolverAInicio = { navController.popBackStack() }
            )
        }
    }
}