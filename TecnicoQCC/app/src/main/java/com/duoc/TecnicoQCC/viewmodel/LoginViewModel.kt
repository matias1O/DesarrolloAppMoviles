package com.duoc.TecnicoQCC.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.TecnicoQCC.model.AppDatabase
import com.duoc.TecnicoQCC.model.UsuarioEntity
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioDao = AppDatabase.obtener(application).usuarioDao()

    var codigoUsuario by mutableStateOf("")
    var password by mutableStateOf("")
    var showError by mutableStateOf(false)

    fun validarLogin(onSuccess: (String) -> Unit) {
        if (codigoUsuario.isNotBlank() && password.isNotBlank()) {
            showError = false
            viewModelScope.launch {
                usuarioDao.guardar(UsuarioEntity(codigoUsuario))
            }
            onSuccess(codigoUsuario)
        } else {
            showError = true
        }
    }
}