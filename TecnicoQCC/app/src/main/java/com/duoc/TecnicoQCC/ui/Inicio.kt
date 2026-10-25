package com.duoc.TecnicoQCC.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InicioScreen() {
    Scaffold(
        bottomBar = {
            // Aquí ira el boton de salida del inicio
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // Encabezado
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "PANEL DE CONTROL")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Bienvenido de nuevo, Matias")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Revisa el estado de tus levantamientos y continúa tu trabajo.")
            }

            //  Botones
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
                            Text("📷")
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(text = "Escanear QR")
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
                // Tarjeta 1
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "📄 12")
                        Text(text = "Total de racks")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tarjeta 2
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "✅ 8")
                        Text(text = "Evaluaciones completadas")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            //  Racks registrados
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Racks registrados")
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

                        Text(text = "RACK-001")
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