package com.duoc.TecnicoQCC.model

import com.google.gson.annotations.SerializedName

data class PerfilUsuario(
    val id: Int = 0,
    @SerializedName("inicialesAvatar")
    val inicialesAvatar: String = "",
    @SerializedName("tipoPerfil")
    val tipoPerfil: String = "PERFIL TÉCNICO",
    val nombre: String = "",
    val password: String = "",
    val cargo: String = "",
    val empresa: String = "",
    val correo: String = "",
    @SerializedName("evaluacionesCompletadas")
    val evaluacionesCompletadas: Int = 0,
    @SerializedName("racksRevisados")
    val racksRevisados: Int = 0
)