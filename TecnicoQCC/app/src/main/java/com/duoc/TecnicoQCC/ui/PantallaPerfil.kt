package com.duoc.TecnicoQCC.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duoc.TecnicoQCC.viewmodel.PerfilViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfil(
    viewModel: PerfilViewModel = viewModel(),
    onVolverAInicio: () -> Unit = {},
    onVerPerfilCompleto: () -> Unit = {},
    onCerrarSesion: () -> Unit = {}
) {
    val perfil by viewModel.perfil.collectAsState()
    val opcionesMenu by viewModel.opcionesMenu.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mi Perfil",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVolverAInicio) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver a Inicio"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF8FAFC)
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // IMPORTANTE: evita que la lista tape la barra superior
                .background(Color(0xFFF8FAFC)),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // 1. Header principal
            item {
                perfil?.let { datosPerfil ->
                    TarjetaPerfil(perfil = datosPerfil)
                }
            }

            // 2. Tarjetas de métricas
            item {
                perfil?.let { datosPerfil ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TarjetaMetrica(
                            valor = datosPerfil.evaluacionesCompletadas.toString(),
                            titulo = "Evaluaciones completadas",
                            icono = Icons.Default.CheckCircle,
                            colorIcono = Color(0xFF16A34A),
                            colorFondoIcono = Color(0xFFDCFCE7),
                            modifier = Modifier.weight(1f)
                        )

                        TarjetaMetrica(
                            valor = datosPerfil.racksRevisados.toString(),
                            titulo = "Racks revisados",
                            icono = Icons.Default.List,
                            colorIcono = Color(0xFF2563EB),
                            colorFondoIcono = Color(0xFFDBEAFE),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // 3. Título de sección
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Cuenta y preferencias",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // 4. Lista de opciones del menú
            items(opcionesMenu) { opcion ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                    FilaOpcionMenu(
                        opcion = opcion,
                        onClick = { /* Próximamente navegación */ }
                    )
                }
            }

            // 5. Botón Ver perfil completo
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onVerPerfilCompleto,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
                ) {
                    Text(
                        text = "Ver perfil completo",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // 6. Botón Cerrar sesión (borra el código guardado en Room y vuelve al login)
            item {
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    onClick = { viewModel.cerrarSesion(onCerrarSesion) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, Color(0xFFFCA5A5)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFB91C1C))
                ) {
                    Text(
                        text = "Cerrar sesión",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}