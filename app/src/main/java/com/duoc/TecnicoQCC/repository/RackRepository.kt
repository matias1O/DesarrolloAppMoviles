package com.duoc.TecnicoQCC.repository

import com.duoc.TecnicoQCC.model.Rack
import com.duoc.TecnicoQCC.remote.RetrofitInstance

class RackRepository {
    suspend fun obtenerRacks(): List<Rack> {
        return RetrofitInstance.api.getRacks()
    }

    suspend fun crearRack(rack: Rack): Rack? {
        val response = RetrofitInstance.api.crearRack(rack)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun eliminarRack(id: String): Boolean {
        return RetrofitInstance.api.eliminarRack(id).isSuccessful
    }

    suspend fun actualizarRack(id: String, rack: Rack): Rack? {
        val response = RetrofitInstance.api.actualizarRack(id, rack)
        return if (response.isSuccessful) response.body() else null
    }
}