package com.duoc.TecnicoQCC.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.TecnicoQCC.model.Rack
import com.duoc.TecnicoQCC.repository.RackRepository
import kotlinx.coroutines.launch

class RackViewModel : ViewModel() {

    private val repository = RackRepository()

    // Lista reactiva observable por Compose (mantiene compatibilidad con tus pantallas)
    val racks = mutableStateListOf<Rack>()

    // Estados opcionales por si deseas mostrar un indicador de carga o error en UI
    var cargando by mutableStateOf(false)
        private set

    var errorMensaje by mutableStateOf<String?>(null)
        private set

    init {
        cargarRacksDesdeApi()
    }

    // GET: Trae los racks desde Xano al abrir la app
    fun cargarRacksDesdeApi() {
        viewModelScope.launch {
            cargando = true
            errorMensaje = null
            try {
                val listaRemota = repository.obtenerRacks()
                racks.clear()
                // Invertimos el orden para que los últimos creados salgan arriba
                racks.addAll(listaRemota.reversed())
            } catch (e: Exception) {
                errorMensaje = "Error al conectar con el servidor: ${e.message}"
                Log.e("RackViewModel", "Error GET racks", e)
            } finally {
                cargando = false
            }
        }
    }

    // POST: Envía el nuevo levantamiento a Xano y actualiza la lista en pantalla
    fun agregarRack(nuevoRack: Rack) {
        viewModelScope.launch {
            cargando = true
            try {
                val rackCreado = repository.crearRack(nuevoRack)
                if (rackCreado != null) {
                    // Agregamos el objeto devuelto por Xano (que ya trae su ID real generado)
                    racks.add(0, rackCreado)
                } else {
                    // Respaldo local si falla la respuesta HTTP
                    racks.add(0, nuevoRack)
                }
            } catch (e: Exception) {
                Log.e("RackViewModel", "Error POST rack", e)
                racks.add(0, nuevoRack)
            } finally {
                cargando = false
            }
        }
    }

    // DELETE: Elimina un rack tanto en Xano como en la lista local
    fun eliminarRack(id: String) {
        viewModelScope.launch {
            try {
                val eliminado = repository.eliminarRack(id)
                if (eliminado) {
                    racks.removeAll { it.id == id }
                }
            } catch (e: Exception) {
                Log.e("RackViewModel", "Error DELETE rack", e)
            }
        }
    }
}