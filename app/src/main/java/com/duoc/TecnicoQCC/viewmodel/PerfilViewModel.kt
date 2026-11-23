package com.duoc.TecnicoQCC.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.TecnicoQCC.model.AppDatabase
import com.duoc.TecnicoQCC.model.OpcionMenu
import com.duoc.TecnicoQCC.model.PerfilUsuario
import com.duoc.TecnicoQCC.repository.PerfilRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PerfilRepository()
    private val usuarioDao = AppDatabase.obtener(application).usuarioDao()

    private val _perfil = MutableStateFlow<PerfilUsuario?>(null)
    val perfil: StateFlow<PerfilUsuario?> = _perfil

    private val _opcionesMenu = MutableStateFlow<List<OpcionMenu>>(emptyList())
    val opcionesMenu: StateFlow<List<OpcionMenu>> = _opcionesMenu

    private val _mensajeEstado = MutableStateFlow<String?>(null)
    val mensajeEstado: StateFlow<String?> = _mensajeEstado

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        viewModelScope.launch {
            usuarioDao.observar().collect { usuario ->
                val codigo = usuario?.codigoUsuario ?: ""
                _perfil.value = repository.obtenerPerfilUsuario(codigo)
            }
        }
        _opcionesMenu.value = repository.obtenerOpcionesMenu()
    }

    // Valida usuario y contraseña contra Xano antes de permitir el ingreso
    fun validarLogin(
        usuario: String,
        password: String,
        onResultado: (Boolean, PerfilUsuario?) -> Unit
    ) {
        viewModelScope.launch {
            val perfilValido = repository.autenticarUsuario(usuario, password)
            if (perfilValido != null) {
                _perfil.value = perfilValido
                onResultado(true, perfilValido)
            } else {
                onResultado(false, null)
            }
        }
    }

    // Guarda cambios: si el usuario ya tiene id > 0 hace PATCH; si no existe en nube hace POST
    fun guardarCambiosPerfil(
        nombre: String,
        cargo: String,
        empresa: String,
        correo: String
    ) {
        val actual = _perfil.value ?: return
        val actualizado = actual.copy(
            inicialesAvatar = nombre.take(2).uppercase(),
            nombre = nombre,
            cargo = cargo,
            empresa = empresa,
            correo = correo
        )

        viewModelScope.launch {
            val resultado = if (actual.id > 0) {
                repository.actualizarUsuarioEnNube(actual.id, actualizado)
            } else {
                repository.registrarUsuarioEnNube(actualizado)
            }

            if (resultado != null) {
                _perfil.value = resultado
                _mensajeEstado.value = "Perfil sincronizado con Xano"
            } else {
                _perfil.value = actualizado
                _mensajeEstado.value = "No se pudo conectar con Xano"
            }
        }
    }

    // Elimina el perfil en Xano (DELETE) y cierra la sesión local en Room
    fun eliminarPerfilYCerrarSesion(onSesionCerrada: () -> Unit) {
        val idActual = _perfil.value?.id ?: 0
        viewModelScope.launch {
            if (idActual > 0) {
                repository.eliminarUsuarioEnNube(idActual)
            }
            usuarioDao.borrarTodo()
            onSesionCerrada()
        }
    }

    fun cerrarSesion(onSesionCerrada: () -> Unit) {
        viewModelScope.launch {
            usuarioDao.borrarTodo()
            onSesionCerrada()
        }
    }

    fun limpiarMensaje() {
        _mensajeEstado.value = null
    }
}