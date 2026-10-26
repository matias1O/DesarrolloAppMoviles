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

    val estaLogueado by userPreferences.estaLogueado.collectAsState(initial = false)

    // Apunta a la ruta de inicio que incluye el parámetro
    val pantallaInicial = if (estaLogueado) "inicio/Usuario" else "login"

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

        //ruta de inicio unificada con todas las acciones
        composable("inicio/{nombreUsuario}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombreUsuario") ?: "Usuario"

            InicioScreen(
                nombreUsuario = nombre,
                onIrAPerfil = { navController.navigate("perfil") },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo(0) // Limpia el historial
                    }
                },
                onNuevoLevantamiento = { navController.navigate("nuevo_levantamiento") },
                onVerDetallesClick = { navController.navigate("detalle_rack") }
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
    }
}