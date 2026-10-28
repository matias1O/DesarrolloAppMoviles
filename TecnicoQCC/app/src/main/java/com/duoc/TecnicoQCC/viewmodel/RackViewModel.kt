package com.duoc.TecnicoQCC.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.duoc.TecnicoQCC.model.Rack

class RackViewModel : ViewModel() {

    // Lista reactiva observable por Compose
    val racks = mutableStateListOf(
        Rack(
            id = "RACK-001",
            nombre = "Sala principal",
            area = "Zona A · LAB-QCC-02",
            fecha = "18 mar 2026",
            inspector = "Laura Mendoza",
            estado = "Completada"
        )
    )

    fun agregarRack(nuevoRack: Rack) {
        racks.add(0, nuevoRack)
    }
}
