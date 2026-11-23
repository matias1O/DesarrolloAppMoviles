package com.duoc.TecnicoQCC.repository

import android.util.Log
import com.duoc.TecnicoQCC.model.OpcionMenu
import com.duoc.TecnicoQCC.model.PerfilUsuario
import com.duoc.TecnicoQCC.remote.RetrofitInstance

class PerfilRepository {

    // GET: Busca el usuario en Xano; si no hay conexión o no existe, usa los datos por defecto
    suspend fun obtenerPerfilUsuario(codigoUsuario: String): PerfilUsuario {
        return try {
            val usuariosRemotos = RetrofitInstance.api.getUsuarios()

            usuariosRemotos.find {
                it.correo.equals(codigoUsuario, ignoreCase = true) ||
                        it.nombre.equals(codigoUsuario, ignoreCase = true)
            } ?: usuariosRemotos.firstOrNull() ?: crearPerfilLocalPorDefecto(codigoUsuario)

        } catch (e: Exception) {
            Log.e("PerfilRepository", "Error al obtener usuario desde Xano", e)
            crearPerfilLocalPorDefecto(codigoUsuario)
        }
    }

    // POST: Crea un nuevo usuario en Xano
    suspend fun registrarUsuarioEnNube(perfil: PerfilUsuario): PerfilUsuario? {
        return try {
            val response = RetrofitInstance.api.crearUsuario(perfil)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            Log.e("PerfilRepository", "Error al crear usuario en Xano", e)
            null
        }
    }

    private fun crearPerfilLocalPorDefecto(codigoUsuario: String): PerfilUsuario {
        return PerfilUsuario(
            inicialesAvatar = if (codigoUsuario.isNotEmpty()) codigoUsuario.take(2).uppercase() else "\uD83D\uDC64",
            tipoPerfil = "PERFIL TÉCNICO",
            nombre = codigoUsuario,
            cargo = "Inspector técnico",
            empresa = "CMC360 SPA",
            correo = codigoUsuario.take(3).uppercase() + ".QCC@gmail.com",
            evaluacionesCompletadas = 0,
            racksRevisados = 0
        )
    }

    fun obtenerOpcionesMenu(): List<OpcionMenu> {
        return listOf(
            OpcionMenu(
                titulo = "Información personal",
                subtitulo = "Datos personales y contacto",
                iconoResId = 0,
                ruta = "info_personal"
            ),
            OpcionMenu(
                titulo = "Cargo y permisos",
                subtitulo = "Rol, accesos y autorizaciones",
                iconoResId = 0,
                ruta = "cargo_permisos"
            ),
            OpcionMenu(
                titulo = "Historial de evaluaciones",
                subtitulo = "Actividad y revisiones anteriores",
                iconoResId = 0,
                ruta = "historial"
            ),
            OpcionMenu(
                titulo = "Configuración",
                subtitulo = "Preferencias de la aplicación",
                iconoResId = 0,
                ruta = "configuracion"
            )
        )
    }
}