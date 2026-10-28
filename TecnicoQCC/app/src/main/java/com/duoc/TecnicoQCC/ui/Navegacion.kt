package com.duoc.TecnicoQCC.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.duoc.TecnicoQCC.model.AppDatabase

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val usuarioDao = remember { AppDatabase.obtener(context).usuarioDao() }

    // Código guardado en Room (null si no hay sesión) y si ya terminamos de leerlo
    var codigoGuardado by remember { mutableStateOf<String?>(null) }
    var roomListo by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        codigoGuardado = usuarioDao.obtener()?.codigoUsuario
        roomListo = true
    }

    // Hasta que Room responda no se crea el NavHost
    if (!roomListo) return

    // El destino inicial es la ruta SIN valores concretos; el código viaja como valor por defecto
    val destinoInicial = if (codigoGuardado != null) "inicio/{nombreUsuario}" else "login"

    NavHost(navController = navController, startDestination = destinoInicial) {

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

        //ruta de inicio unificada con todas las acciones
        composable(
            route = "inicio/{nombreUsuario}",
            arguments = listOf(
                navArgument("nombreUsuario") {
                    type = NavType.StringType
                    defaultValue = codigoGuardado ?: "Usuario"
                }
            )
        ) { backStackEntry ->
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
    }
}