package com.duoc.TecnicoQCC.repository

import android.util.Log
import com.duoc.TecnicoQCC.model.OpcionMenu
import com.duoc.TecnicoQCC.model.PerfilUsuario
import com.duoc.TecnicoQCC.model.UsuarioDao
import com.duoc.TecnicoQCC.remote.RetrofitInstance

class PerfilRepository {

    // 1. GET: Obtener perfil desde Xano
    suspend fun obtenerPerfilUsuario(codigoUsuario: String): PerfilUsuario {
        return try {
            val usuariosRemotos = RetrofitInstance.api.getUsuarios()
            usuariosRemotos.find {
                it.correo.equals(codigoUsuario.trim(), ignoreCase = true) ||
                        it.nombre.equals(codigoUsuario.trim(), ignoreCase = true)
            } ?: crearPerfilLocalPorDefecto(codigoUsuario)
        } catch (e: Exception) {
            Log.e("PerfilRepository", "Error GET usuario desde Xano", e)
            crearPerfilLocalPorDefecto(codigoUsuario)
        }
    }

    suspend fun autenticarUsuario(usuarioInput: String, passwordInput: String): PerfilUsuario? {
        if (usuarioInput.isBlank() || passwordInput.isBlank()) return null
        return try {
            val usuariosRemotos = RetrofitInstance.api.getUsuarios()
            usuariosRemotos.find {
                (it.correo.equals(usuarioInput.trim(), ignoreCase = true) ||
                        it.nombre.equals(usuarioInput.trim(), ignoreCase = true)) &&
                        it.password.isNotBlank() &&
                        it.password == passwordInput
            }
        } catch (e: Exception) {
            Log.e("PerfilRepository", "Error autenticando en Xano", e)
            null
        }
    }



    // 2. POST: Crear usuario en Xano
    suspend fun registrarUsuarioEnNube(perfil: PerfilUsuario): PerfilUsuario? {
        return try {
            val response = RetrofitInstance.api.crearUsuario(perfil)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            Log.e("PerfilRepository", "Error POST usuario en Xano", e)
            null
        }
    }

    // 3. PATCH / PUT: Actualizar usuario en Xano
    suspend fun actualizarUsuarioEnNube(id: Int, perfil: PerfilUsuario): PerfilUsuario? {
        return try {
            val response = RetrofitInstance.api.editarUsuario(id, perfil)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            Log.e("PerfilRepository", "Error PATCH usuario en Xano", e)
            null
        }
    }

    // 4. DELETE: Eliminar usuario en Xano
    suspend fun eliminarUsuarioEnNube(id: Int): Boolean {
        return try {
            val response = RetrofitInstance.api.eliminarUsuario(id)
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("PerfilRepository", "Error DELETE usuario en Xano", e)
            false
        }
    }

    private fun crearPerfilLocalPorDefecto(codigoUsuario: String): PerfilUsuario {
        return PerfilUsuario(
            id = 0,
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
                subtitulo = "Editar nombre, cargo y correo",
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