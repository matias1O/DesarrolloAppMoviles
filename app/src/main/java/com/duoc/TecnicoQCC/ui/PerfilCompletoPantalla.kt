package com.duoc.TecnicoQCC.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duoc.TecnicoQCC.viewmodel.PerfilViewModel

private data class ActividadReciente(
    val rack: String,
    val estado: String,
    val fecha: String
)

@Composable
fun PerfilCompletoPantalla(
    viewModel: PerfilViewModel = viewModel(),
    onVolverClick: () -> Unit = {},
    onEditarPerfilClick: () -> Unit = {}
) {
    val perfil by viewModel.perfil.collectAsState()

    // Datos fijos de la vista (proyecto de prueba)
    val telefono = "+56 9 4567 8932"
    val ubicacion = "Santiago, Chile"
    val contactoEmergencia = "+56 2 2345 6789"
    val cuentaCreada = "14 ene 2025"
    val responsabilidades = listOf(
        "Inspección técnica de racks y servidores",
        "Validación de componentes de infraestructura",
        "Registro de evidencias y observaciones",
        "Seguimiento de incidencias críticas"
    )
    val actividad = listOf(
        ActividadReciente("RACK-001", "Evaluación completada", "18 mar 2026"),
        ActividadReciente("RACK-004", "Evidencia actualizada", "15 mar 2026"),
        ActividadReciente("RACK-002", "Revisión iniciada", "12 mar 2026")
    )

    Scaffold(containerColor = Color(0xFFF8FAFC)) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botón volver
            item {
                Button(
                    onClick = onVolverClick,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F172A))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Volver a Mi perfil", color = Color.White)
                }
            }

            // Cabecera
            item {
                perfil?.let { datos ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(90.dp)
                                    .background(color = Color(0xFF2563EB), shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = datos.inicialesAvatar,
                                    color = Color.White,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = datos.nombre,
                                color = Color.White,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier
                                    .background(Color(0xFF134E3A), RoundedCornerShape(50))
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(Color(0xFF4ADE80), CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Cuenta activa",
                                    color = Color(0xFF4ADE80),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = datos.cargo,
                                color = Color(0xFF60A5FA),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = datos.empresa,
                                color = Color(0xFF94A3B8),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            // Información de contacto
            item {
                perfil?.let { datos ->
                    TarjetaSeccion {
                        EtiquetaSeccion("Información de contacto")
                        Spacer(modifier = Modifier.height(14.dp))
                        DatoPerfil("Correo electrónico", datos.correo)
                        Spacer(modifier = Modifier.height(14.dp))
                        DatoPerfil("Teléfono", telefono)
                        Spacer(modifier = Modifier.height(14.dp))
                        DatoPerfil("Ubicación", ubicacion)
                        Spacer(modifier = Modifier.height(14.dp))
                        DatoPerfil("Contacto de emergencia", contactoEmergencia)
                    }
                }
            }

            // Datos de la cuenta
            item {
                perfil?.let { datos ->
                    TarjetaSeccion {
                        EtiquetaSeccion("Datos de la cuenta")
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            // El código de usuario guardado en Room (PerfilViewModel lo entrega en "nombre")
                            DatoPerfil(
                                etiqueta = "ID de usuario",
                                valor = datos.nombre,
                                modifier = Modifier.weight(1f)
                            )
                            DatoPerfil(
                                etiqueta = "Cuenta creada",
                                valor = cuentaCreada,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Responsabilidades asignadas
            item {
                TarjetaSeccion {
                    EtiquetaSeccion("Responsabilidades asignadas")
                    responsabilidades.forEach { responsabilidad ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(Color(0xFFDCFCE7), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = Color(0xFF16A34A),
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = responsabilidad,
                                fontSize = 15.sp,
                                color = Color(0xFF334155)
                            )
                        }
                    }
                }
            }

            // Actividad reciente
            item {
                TarjetaSeccion {
                    EtiquetaSeccion("Actividad reciente")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Evaluaciones",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A)
                    )
                    actividad.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color(0xFFDBEAFE), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.List,
                                    contentDescription = null,
                                    tint = Color(0xFF2563EB),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = item.rack,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0F172A)
                                )
                                Text(
                                    text = item.estado,
                                    fontSize = 14.sp,
                                    color = Color(0xFF475569)
                                )
                                Text(
                                    text = item.fecha,
                                    fontSize = 12.sp,
                                    color = Color(0xFF94A3B8)
                                )
                            }
                        }
                    }
                }
            }

            // Botón Editar perfil
            item {
                Button(
                    onClick = onEditarPerfilClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2563EB))
                ) {
                    Text(
                        text = "Editar perfil",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun TarjetaSeccion(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            content = content
        )
    }
}

@Composable
private fun EtiquetaSeccion(texto: String) {
    Text(
        text = texto.uppercase(),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        color = Color(0xFF2563EB)
    )
}

@Composable
private fun DatoPerfil(
    etiqueta: String,
    valor: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = etiqueta.uppercase(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF94A3B8)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = valor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF0F172A)
        )
    }
}