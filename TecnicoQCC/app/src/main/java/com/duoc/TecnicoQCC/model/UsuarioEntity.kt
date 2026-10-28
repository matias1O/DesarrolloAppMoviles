package com.duoc.TecnicoQCC.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity(

    @PrimaryKey val codigoUsuario: String

)