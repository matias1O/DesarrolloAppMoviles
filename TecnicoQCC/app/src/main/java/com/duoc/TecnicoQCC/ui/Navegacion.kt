package com.duoc.TecnicoQCC.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.duoc.TecnicoQCC.repository.UserPreferences
import androidx.compose.runtime.getValue

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    // 1. Iniciamos los estados como "null" para esperar a leer la memoria
    val estaLogueado by userPreferences.estaLogueado.collectAsState(initial = null)
    val nombreGuardado by userPreferences.nombreUsuario.collectAsState(initial = null)

    // 2. Si todavía son null, mostramos un circulo de carga y ESPERAMOS
    if (estaLogueado == null || nombreGuardado == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    // Una ves se tiene los datos se arma la ruta
    val pantallaInicial = if (estaLogueado == true) "inicio/$nombreGuardado" else "login"

    NavHost(navController = navController, startDestination = pantallaInicial) {

        composable("login") {
            QCCLoginScreenSimple(
                onLoginSuccess = { nombreIngresado ->
                    navController.navigate("inicio/$nombreIngresado") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }


        composable(
            route = "inicio/{nombreUsuario}",
            arguments = listOf(navArgument("nombreUsuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombreUsuario")?.takeIf { it.isNotBlank() } ?: "Usuario"

            InicioScreen(
                nombreUsuario = nombre,
                onIrAPerfil = { navController.navigate("perfil") },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo(0) // Limpia el historial
                    }
                },
                onNuevoLevantamiento = { navController.navigate("nuevo_levantamiento") },
                onVerDetallesClick = { navController.navigate("detalle_rack") },
                onAbrirCamara = { navController.navigate("camara") }
            )
        }

        composable("perfil") {
            PantallaPerfil(
                onVolverAInicio = { navController.popBackStack() }
            )
        }

        composable("nuevo_levantamiento"){
            NuevoLevantamientoPantalla(
                onVolverClick = { navController.popBackStack() },
                onGuardarClick = { nuevoRack ->
                    navController.popBackStack()
                }
            )
        }

        composable("detalle_rack"){
            DetalleRackPantalla(
                onVolverClick = { navController.popBackStack() }
            )
        }

        composable("camara") {
            CamaraScreen(
                onVolverClick = { navController.popBackStack() }
            )
        }
        composable("login") {
            QCCLoginScreenSimple(
                onLoginSuccess = { nombreIngresado ->
                    navController.navigate("inicio/$nombreIngresado") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onAbrirCamara = { navController.navigate("camara") }
            )
        }
    }
}