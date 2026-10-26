package com.duoc.TecnicoQCC.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    onIrAPerfil: () -> Unit = {} // Callback para navegar
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel de Control") },
                actions = {
                    // Botón de Perfil en la esquina superior derecha
                    IconButton(onClick = onIrAPerfil) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Ir al Perfil"
                        )
                    }
                }
            )
        },
        bottomBar = {
            // Aquí irá el botón de salida del inicio
        }
    ) { paddingValues ->

        // Se agrega verticalScroll para que el contenido no se corte en pantallas pequeñas
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // Encabezado
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Bienvenido de nuevo, Matias",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Revisa el estado de tus levantamientos y continúa tu trabajo.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Botones principales
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Button(
                    onClick = { /* TODO: Navegar a nueva vista */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "+ Agregar nuevo levantamiento")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* TODO: Abrir cámara/QR */ },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.size(48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("📷", fontSize = 24.sp)
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(text = "Escanear QR", fontWeight = FontWeight.Bold)
                            Text(text = "Iniciar nuevo levantamiento")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjetas SIMPLES
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "📄 12", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Total de racks")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "✅ 8", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Evaluaciones completadas")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Racks registrados
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Racks registrados", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "Consulta y administra las inspecciones recientes.")

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Buscar por identificador...") },
                    leadingIcon = { Text("🔍") },
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "RACK-001", fontWeight = FontWeight.Bold)
                        Text(text = "Sala principal · Zona A")

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedButton(
                            onClick = { /* TODO */ },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Ver detalles")
                        }
                    }
                }
            }
        }
    }
}