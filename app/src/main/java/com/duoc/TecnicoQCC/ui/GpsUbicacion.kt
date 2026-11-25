package com.duoc.TecnicoQCC.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

@Composable
fun TarjetaGps(
    latitud: Double?,
    longitud: Double?,
    onUbicacionObtenida: (Double, Double) -> Unit
) {
    val context = LocalContext.current
    val fusedClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var cargando by remember { mutableStateOf(false) }

    // Obtiene las coordenadas actuales del GPS
    @SuppressLint("MissingPermission")
    fun obtenerUbicacion() {
        cargando = true
        fusedClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener { loc ->
            cargando = false
            if (loc != null) {
                onUbicacionObtenida(loc.latitude, loc.longitude)
            } else {
                Toast.makeText(context, "No se pudo obtener la ubicación. ¿GPS activado?", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            cargando = false
            Toast.makeText(context, "Error al obtener ubicación", Toast.LENGTH_SHORT).show()
        }
    }

    // Solicita los permisos de ubicación
    val permisoLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permisos ->
        if (permisos[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permisos[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            obtenerUbicacion()
        } else {
            Toast.makeText(context, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }

    // Interfaz visual de la tarjeta GPS
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                "Ubicación GPS",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (latitud != null && longitud != null) "Lat: $latitud\nLng: $longitud"
                else "Sin ubicación registrada",
                color = Color(0xFF475569)
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Botón principal para lanzar la solicitud de permisos
            Button(
                onClick = {
                    permisoLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                },
                enabled = !cargando,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(if (cargando) "Obteniendo..." else "Obtener ubicación")
            }

            // Abre la ubicación registrada en una app externa de mapas
            if (latitud != null && longitud != null) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = {
                        val uri = Uri.parse("geo:$latitud,$longitud?q=$latitud,$longitud(Rack)")
                        try {
                            context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context, "No hay app de mapas instalada", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Abrir en Google Maps")
                }
            }
        }
    }
}