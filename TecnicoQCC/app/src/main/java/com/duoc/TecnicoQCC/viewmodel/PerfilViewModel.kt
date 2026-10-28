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

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        viewModelScope.launch {
            usuarioDao.observar().collect { usuario ->
                val nombreGuardado = usuario?.codigoUsuario ?: ""
                val perfilBase = repository.obtenerPerfilUsuario()

                _perfil.value = perfilBase?.copy(nombre = nombreGuardado)
                    ?: PerfilUsuario(
                        nombre = nombreGuardado,
                        correo = "usuario@tecnicoqcc.cl",
                        empresa = "Duoc UC / QCC",
                        inicialesAvatar = if (nombreGuardado.isNotEmpty()) nombreGuardado.take(2).uppercase() else "TQ",
                        tipoPerfil = "Técnico Operativo",
                        cargo = "Técnico QCC",
                        evaluacionesCompletadas = 8,
                        racksRevisados = 12
                    )
            }
        }
        _opcionesMenu.value = repository.obtenerOpcionesMenu()
    }

    fun cerrarSesion(onSesionCerrada: () -> Unit) {
        viewModelScope.launch {
            usuarioDao.borrarTodo()
            onSesionCerrada()
        }
    }
}