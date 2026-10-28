package com.duoc.TecnicoQCC.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.duoc.TecnicoQCC.model.AppDatabase
import com.duoc.TecnicoQCC.viewmodel.RackViewModel

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val usuarioDao = remember { AppDatabase.obtener(context).usuarioDao() }

    // Instancia única y compartida del ViewModel de racks
    val rackViewModel: RackViewModel = viewModel(
        viewModelStoreOwner = (context as androidx.activity.ComponentActivity)
    )

    // Código guardado (null si no hay sesión) y si ya terminamos de leerlo
    var codigoGuardado by remember { mutableStateOf<String?>(null) }
    var roomListo by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        codigoGuardado = usuarioDao.obtener()?.codigoUsuario
        roomListo = true
    }

    // Hasta que termine de leer no se crea el NavHost
    if (!roomListo) return

    val destinoInicial = if (codigoGuardado != null) "inicio/{nombreUsuario}" else "login"

    NavHost(
        navController = navController,
        startDestination = destinoInicial,
        // Entrada hacia adelante (desliza desde la derecha)
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        // Salida hacia adelante (se desplaza a la izquierda)
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        // Regreso/Atrás (la pantalla anterior entra desde la izquierda)
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        // Salida al ir Atrás (la pantalla actual se desliza a la derecha)
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {

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

        // Ruta de inicio unificada con todas las acciones
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
                rackViewModel = rackViewModel,
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
                onVolverAInicio = { navController.popBackStack() },
                onVerPerfilCompleto = { navController.navigate("perfil_completo") },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo(0) // Limpia el historial
                    }
                }
            )
        }

        composable("perfil_completo") {
            PerfilCompletoPantalla(
                onVolverClick = { navController.popBackStack() }
            )
        }

        composable("nuevo_levantamiento") {
            NuevoLevantamientoPantalla(
                onVolverClick = { navController.popBackStack() },
                onGuardarClick = { nuevoRack ->
                    rackViewModel.agregarRack(nuevoRack)
                    navController.popBackStack()
                }
            )
        }

        composable("detalle_rack") {
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