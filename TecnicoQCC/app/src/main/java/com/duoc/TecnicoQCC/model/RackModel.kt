package com.duoc.TecnicoQCC.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class Componente(
    val nombre: String,
    val cantidad: Int,
    val estado: String
)

data class Rack(
    val id: String,
    val nombre: String,
    val area: String,
    val fecha: String,
    val inspector: String,
    val estado: String,
    val componentes: List<Componente> = emptyList(),
    val observaciones: String = ""
)

