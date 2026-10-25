package com.duoc.TecnicoQCC.viewmodel

import com.duoc.TecnicoQCC.model.OpcionMenu
import com.duoc.TecnicoQCC.model.PerfilUsuario
import com.duoc.TecnicoQCC.repository.PerfilRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel {

    private val repository = PerfilRepository()

    private val _perfil = MutableStateFlow<PerfilUsuario?>(null)
    val perfil: StateFlow<PerfilUsuario?> = _perfil

    private val _opcionesMenu = MutableStateFlow<List<OpcionMenu>>(emptyList())
    val opcionesMenu: StateFlow<List<OpcionMenu>> = _opcionesMenu

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        _perfil.value = repository.obtenerPerfilUsuario()
        _opcionesMenu.value = repository.obtenerOpcionesMenu()

    }
}