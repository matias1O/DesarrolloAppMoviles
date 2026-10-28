package com.duoc.TecnicoQCC.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.TecnicoQCC.model.AppDatabase

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val usuarioDao = remember { AppDatabase.obtener(context).usuarioDao() }

    // null = todavía leyendo Room
    var pantallaInicial by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val usuario = usuarioDao.obtener()
        pantallaInicial = usuario?.let { "inicio/${it.codigoUsuario}" } ?: "login"
    }

    // Hasta que Room responda no se crea el NavHost
    val destinoInicial = pantallaInicial ?: return

    NavHost(navController = navController, startDestination = destinoInicial) {

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