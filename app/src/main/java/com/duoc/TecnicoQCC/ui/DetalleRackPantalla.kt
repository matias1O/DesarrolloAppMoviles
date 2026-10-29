package com.duoc.TecnicoQCC.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.TecnicoQCC.model.Componente
import com.duoc.TecnicoQCC.model.Rack

@Composable
fun DetalleRackPantalla(
    // Datos por defecto (mock data) para poder probarla de inmediato
    rack: Rack = Rack(
        id = "RACK-001",
        nombre = "Sala principal · Zona A",
        area = "LAB-QCC-02",
        fecha = "18 mar 2026",
        inspector = "Matias Vera",
        estado = "Completada",
        componentes = listOf(
            Componente("Servidor", 8, "Verificado"),
            Componente("Switch de red", 3, "Verificado"),
            Componente("Router", 2, "Requiere revisión"),
            Componente("Patch Panel", 4, "Verificado"),
            Componente("Firewall", 1, "Pendiente")
        )
    ),
    onVolverClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = Color(0xFFF8FAFC)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // 1. Botón "Volver al panel principal"
            Button(
                onClick = onVolverClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F172A)),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Volver al panel principal", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }

            // 2. Imagen Mock del Rack con texto superpuesto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFF064E3B)) // Verde oscuro simulando la iluminación del rack
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(18.dp)
                ) {
                    Text(
                        text = "RACK SELECCIONADO",
                        color = Color(0xFF6EE7B7),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = rack.id,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // 3. Identificación y Estado
            Column {
                Text(
                    text = rack.id,
                    color = Color(0xFF1D61FF),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = rack.nombre,
                    color = Color(0xFF0F172A),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Badge de estado verde suave
                Surface(
                    color = Color(0xFFDCFCE7),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = rack.estado,
                        color = Color(0xFF166534),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }

            // 4. Tarjetas de información clave (Gris claro)
            InfoCard(titulo = "ÁREA / LABORATORIO", valor = rack.area)
            InfoCard(titulo = "ÚLTIMA EVALUACIÓN", valor = rack.fecha)

            // Inspector con avatar
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "INSPECTOR RESPONSABLE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF64748B),
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1D61FF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("LM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(rack.inspector, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A), fontSize = 14.sp)
                            Text("Técnico de infraestructura QCC", color = Color(0xFF64748B), fontSize = 12.sp)
                        }
                    }
                }
            }

            // 5. Inventario Técnico (Componentes)
            Column {
                Text(
                    text = "INVENTARIO TÉCNICO",
                    color = Color(0xFF1D61FF),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "Componentes identificados",
                    color = Color(0xFF0F172A),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "18 unidades registradas",
                    color = Color(0xFF64748B),
                    fontSize = 13.sp
                )
            }

            // Tarjeta contenedora con la lista de componentes divididos
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    rack.componentes.forEachIndexed { index, comp ->
                        FilaComponente(componente = comp)
                        if (index < rack.componentes.size - 1) {
                            HorizontalDivider(color = Color(0xFFF1F5F9), thickness = 1.dp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // 6. Botones inferiores de acción
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D61FF))
            ) {
                Text("Revisar componentes", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Editar información", color = Color(0xFF0F172A), fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Ver evidencia", color = Color(0xFF0F172A), fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Mini-componente reutilizable para las tarjetas grises de info
@Composable
fun InfoCard(titulo: String, valor: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = titulo,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF64748B),
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = valor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
        }
    }
}

// Mini-componente para cada fila del inventario
@Composable
fun FilaComponente(componente: Componente) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFFE0E7FF), shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Description,
                contentDescription = null,
                tint = Color(0xFF1D61FF),
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = componente.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(0xFF0F172A)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Cantidad: ${componente.cantidad}",
                color = Color(0xFF64748B),
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(6.dp))

            // Color del badge según el estado
            val (bgColor, txtColor) = when (componente.estado) {
                "Verificado" -> Color(0xFFDCFCE7) to Color(0xFF166534)
                "Requiere revisión" -> Color(0xFFFEF3C7) to Color(0xFFB45309)
                else -> Color(0xFFF1F5F9) to Color(0xFF64748B)
            }

            Surface(
                color = bgColor,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = componente.estado,
                    color = txtColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        }
    }
}
