package com.duoc.TecnicoQCC

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.duoc.TecnicoQCC.ui.AppNavegacion
import com.duoc.TecnicoQCC.ui.PantallaPerfil
import com.duoc.TecnicoQCC.ui.QCCLoginScreenSimple
import com.duoc.TecnicoQCC.ui.theme.TecnicoQCCTheme


class
MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TecnicoQCCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavegacion()
                    PantallaPerfil()
                }
            }
        }
    }
}

