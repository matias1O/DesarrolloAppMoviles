package com.duoc.TecnicoQCC.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable

fun QCCLoginScreenSimple(onLoginSuccess: () -> Unit) {
    var codigoUsuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        // TÍTULO
        Text(text = "Bienvenido a QCC", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(32.dp)) //

        // CAMPO DE USUARIO
        OutlinedTextField(
            value = codigoUsuario,
            onValueChange = { codigoUsuario = it },
            label = { Text("Introduce tu codigo de usuario") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //CAMPO DE CONTRASEÑA
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Introduce tu contraseña") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // BOTÓN PRINCIPAL
        Button(
            onClick = {onLoginSuccess()},
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Ingresar")
        }

        Spacer(modifier = Modifier.height(32.dp))

        //  BOTÓN SECUNDARIO (QR)
        Text(text = "Acceso alternativo")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text("Solicitar código QR")
        }
    }
}