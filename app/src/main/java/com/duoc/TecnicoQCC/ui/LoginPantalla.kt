package com.duoc.TecnicoQCC.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.TecnicoQCC.R
import com.duoc.TecnicoQCC.model.AppDatabase
import com.duoc.TecnicoQCC.model.UsuarioEntity
import kotlinx.coroutines.launch

@Composable
fun QCCLoginScreenSimple(
    onLoginSuccess: (String) -> Unit,
    onAbrirCamara: () -> Unit = {}
) {
    var codigoUsuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val usuarioDao = remember { AppDatabase.obtener(context).usuarioDao() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        // LOGO
        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "Logo de la empresa",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 48.dp, start = 24.dp)
                .size(84.dp)
        )

        // TARJETA DEL FORMULARIO
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bienvenido a QCC",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ingresa tus credenciales para continuar",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Campo Código de Usuario
                OutlinedTextField(
                    value = codigoUsuario,
                    onValueChange = {
                        codigoUsuario = it
                        if (mensajeError != null) mensajeError = null // Se quita el error al escribir
                    },
                    label = { Text("Código de usuario") },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    },
                    isError = mensajeError != null && codigoUsuario.isBlank(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo Contraseña
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        if (mensajeError != null) mensajeError = null // Se quita el error al escribir
                    },
                    label = { Text("Contraseña") },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    },
                    isError = mensajeError != null && password.isBlank(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Mensaje de Error
                AnimatedVisibility(
                    visible = mensajeError != null,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    mensajeError?.let { error ->
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, start = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botón de Ingreso con Validación
                Button(
                    onClick = {
                        when {
                            codigoUsuario.isBlank() && password.isBlank() -> {
                                mensajeError = "Ingresa tu usuario y contraseña"
                            }
                            codigoUsuario.isBlank() -> {
                                mensajeError = "El código de usuario no puede estar vacío"
                            }
                            password.isBlank() -> {
                                mensajeError = "La contraseña no puede estar vacía"
                            }
                            else -> {
                                mensajeError = null
                                scope.launch {
                                    usuarioDao.guardar(UsuarioEntity(codigoUsuario.trim()))
                                    onLoginSuccess(codigoUsuario.trim())
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Ingresar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Acceso alternativo",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = onAbrirCamara,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Solicitar código QR")
                }
            }
        }
    }
}