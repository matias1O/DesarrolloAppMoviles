package com.duoc.TecnicoQCC.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Text
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

@Composable
fun PantallaPerfil(
    viewModel: PerfilViewModel = viewModel()
) {
    val perfil by viewModel.perfil.collectAsState()
    val opcionesMenu by viewModel.opcionesMenu.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
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
    }
}
