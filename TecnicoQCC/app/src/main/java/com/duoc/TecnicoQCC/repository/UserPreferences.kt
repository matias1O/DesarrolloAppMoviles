package com.duoc.TecnicoQCC.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensión para instanciar DataStore
private val Context.dataStore by preferencesDataStore(name = "sesion_usuario")

class UserPreferences(private val context: Context) {

    companion object {
        val KEY_NOMBRE_USUARIO = stringPreferencesKey("nombre_usuario")
        val KEY_SESION_ACTIVA = booleanPreferencesKey("sesion_activa")
    }

    // Obtener el nombre guardado
    val nombreUsuario: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[KEY_NOMBRE_USUARIO] ?: "Usuario"
    }

    // Obtener el estado de la sesión
    val estaLogueado: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEY_SESION_ACTIVA] ?: false
    }

    // Guardar los datos del login
    suspend fun guardarSesion(nombre: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_NOMBRE_USUARIO] = nombre
            preferences[KEY_SESION_ACTIVA] = true
        }
    }

    // Cerrar sesión
    suspend fun cerrarSesion() {
        context.dataStore.edit { preferences ->
            preferences[KEY_SESION_ACTIVA] = false
            preferences[KEY_NOMBRE_USUARIO] = ""
        }
    }
}