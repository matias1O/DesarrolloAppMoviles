package com.duoc.TecnicoQCC.model

data class PerfilUsuario(
    val inicialesAvatar : String,
    val tipoPerfil: String,
    val nombre: String,
    val cargo: String,
    val empresa: String,
    val correo: String,
    val evaluacionesCompletadas: Int,
    val racksRevisados: Int

)