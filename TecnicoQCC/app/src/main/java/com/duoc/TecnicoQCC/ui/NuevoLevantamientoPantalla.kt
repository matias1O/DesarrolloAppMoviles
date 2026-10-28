package com.duoc.TecnicoQCC.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.TecnicoQCC.model.Componente
import com.duoc.TecnicoQCC.model.Rack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoLevantamientoPantalla(
    onVolverClick: () -> Unit = {},
    onGuardarClick: (Rack) -> Unit = {}
) {
    // --- ESTADOS (useState) ---
    // Paso 1
    var idRack by remember { mutableStateOf("") }
    var nombreRack by remember { mutableStateOf("") }
    var areaLaboratorio by remember { mutableStateOf("") }

    // Paso 2
    var fecha by remember { mutableStateOf("18/03/2026") }
    var inspector by remember { mutableStateOf("Matias Vera") }
    var estadoSeleccionado by remember { mutableStateOf("En progreso") }
    var expandedEstado by remember { mutableStateOf(false) }
    val estados = listOf("En progreso", "Completada", "Pendiente")

    // Paso 3
    var categoriaSeleccionada by remember { mutableStateOf("Servidor") }
    var expandedCategoria by remember { mutableStateOf(false) }
    val categorias = listOf("Servidor", "Switch de red", "Router", "Patch Panel", "UPS")
    var descripcionComponente by remember { mutableStateOf("") }
    var cantidadComponente by remember { mutableStateOf("1") }

    Scaffold(
        topBar = {
            // Header oscuro superior fijo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0D1B2A))
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onVolverClick, // Vuelve atrás al hacer clic
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF1E293B), shape = RoundedCornerShape(10.dp))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "CMC360 SPA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF60A5FA)
                    )
                    Text(
                        text = "Nuevo levantamiento",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        },
        bottomBar = {
            // Footer fijo abajo (position: fixed; bottom: 0)
            Surface(
                color = Color.White,
                shadowElevation = 10.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Botón Cancelar (borde gris)
                    OutlinedButton(
                        onClick = onVolverClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = "Cancelar",
                            color = Color(0xFF334155),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )
                    }

                    // Botón Guardar levantamiento (azul relleno)
                    // Botón Guardar levantamiento (azul relleno)
                    Button(
                        onClick = {
                            val nuevoRack = Rack(
                                id = idRack.trim().ifBlank { "RACK-00" + (10..99).random() },
                                nombre = nombreRack.trim().ifBlank { "Rack Nuevo" },
                                area = areaLaboratorio.trim().ifBlank { "Área General" },
                                fecha = fecha,
                                inspector = inspector,
                                estado = estadoSeleccionado,
                                componentes = listOf(
                                    Componente(
                                        nombre = "$categoriaSeleccionada - ${descripcionComponente.ifBlank { "General" }}",
                                        cantidad = cantidadComponente.toIntOrNull() ?: 1,
                                        estado = "Operativo"
                                    )
                                )
                            )
                            onGuardarClick(nuevoRack)
                        },
                        modifier = Modifier
                            .weight(1.3f)
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D61FF))
                    ) {
                        Text(
                            text = "Guardar levantamiento",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )
                    }

                }
            }
        }
    ) { paddingValues ->
        // Contenido scrolleable central (overflow-y: auto)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1F5F9))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // ==================== PASO 1 ====================
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color(0xFFE0E7FF), shape = RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Description,
                                contentDescription = null,
                                tint = Color(0xFF1D61FF),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("PASO 1", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1D61FF))
                            Text("Identificación del rack", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("ID del rack", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = idRack,
                        onValueChange = { idRack = it },
                        placeholder = { Text("Ej. RACK-008", color = Color(0xFF94A3B8)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("Nombre del rack", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = nombreRack,
                        onValueChange = { nombreRack = it },
                        placeholder = { Text("Ej. Sala de comunicaciones", color = Color(0xFF94A3B8)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("Área o laboratorio", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = areaLaboratorio,
                        onValueChange = { areaLaboratorio = it },
                        placeholder = { Text("Ej. LAB-QCC-02", color = Color(0xFF94A3B8)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            }

            // ==================== PASO 2 ====================
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color(0xFFE0E7FF), shape = RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Description,
                                contentDescription = null,
                                tint = Color(0xFF1D61FF),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("PASO 2", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1D61FF))
                            Text("Información del levantamiento", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Fecha", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = fecha,
                        onValueChange = { fecha = it },
                        trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("Inspector responsable", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = inspector,
                        onValueChange = { inspector = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text("Estado", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0F172A))
                    Spacer(modifier = Modifier.height(4.dp))
                    ExposedDropdownMenuBox(
                        expanded = expandedEstado,
                        onExpandedChange = { expandedEstado = !expandedEstado }
                    ) {
                        OutlinedTextField(
                            value = estadoSeleccionado,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedEstado) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = expandedEstado,
                            onDismissRequest = { expandedEstado = false }
                        ) {
                            estados.forEach { est ->
                                DropdownMenuItem(
                                    text = { Text(est) },
                                    onClick = {
                                        estadoSeleccionado = est
                                        expandedEstado = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // ==================== PASO 3 ====================
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color(0xFFE0E7FF), shape = RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Description,
                                    contentDescription = null,
                                    tint = Color(0xFF1D61FF),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("PASO 3", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1D61FF))
                                Text("Componentes", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                            }
                        }

                        // Botón + Agregar
                        TextButton(onClick = { }) {
                            Text("+ Agregar", color = Color(0xFF1D61FF), fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Sub-tarjeta interna gris para los campos del componente
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("Categoría", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF475569))
                            Spacer(modifier = Modifier.height(4.dp))
                            ExposedDropdownMenuBox(
                                expanded = expandedCategoria,
                                onExpandedChange = { expandedCategoria = !expandedCategoria }
                            ) {
                                OutlinedTextField(
                                    value = categoriaSeleccionada,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategoria) },
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
                                )
                                ExposedDropdownMenu(
                                    expanded = expandedCategoria,
                                    onDismissRequest = { expandedCategoria = false }
                                ) {
                                    categorias.forEach { cat ->
                                        DropdownMenuItem(
                                            text = { Text(cat) },
                                            onClick = {
                                                categoriaSeleccionada = cat
                                                expandedCategoria = false
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Descripción", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF475569))
                            Spacer(modifier = Modifier.height(4.dp))
                            OutlinedTextField(
                                value = descripcionComponente,
                                onValueChange = { descripcionComponente = it },
                                placeholder = { Text("Marca, modelo o detalle", color = Color(0xFF94A3B8)) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Cantidad", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF475569))
                            Spacer(modifier = Modifier.height(4.dp))
                            OutlinedTextField(
                                value = cantidadComponente,
                                onValueChange = { cantidadComponente = it },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
                            )
                        }
                    }
                }
            }

            // Espacio final para que el último card no quede cortado por la barra de botones
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
