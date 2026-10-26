package com.duoc.TecnicoQCC.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var codigoUsuario by mutableStateOf("")
    var password by mutableStateOf("")
    var showError by mutableStateOf(false)


    fun validarLogin(onSuccess: (String) -> Unit) {
        if (codigoUsuario.isNotBlank() && password.isNotBlank()) {
            showError = false
            onSuccess(codigoUsuario)
        } else {
            showError = true
        }
    }
}