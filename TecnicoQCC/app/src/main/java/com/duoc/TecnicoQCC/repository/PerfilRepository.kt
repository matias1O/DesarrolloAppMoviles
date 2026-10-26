package com.duoc.TecnicoQCC.repository

import com.duoc.TecnicoQCC.model.OpcionMenu
import com.duoc.TecnicoQCC.model.PerfilUsuario

class PerfilRepository {


    fun obtenerPerfilUsuario(): PerfilUsuario{
        return PerfilUsuario(
            inicialesAvatar = "LP",
            tipoPerfil = "PERFIL TÉCNICO",
            nombre = "Lucas Pizarro",
            cargo = "Inspecctor técnico",
            empresa = "CMC360 SPA",
            correo = "luc.piza@duocuc,cl",
            evaluacionesCompletadas = 28,
            racksRevisados = 16

        )
    }

    fun obtenerOpcionesMenu(): List<OpcionMenu> {
        return listOf(
            OpcionMenu(
                titulo = "Información personal",
                subtitulo = "Datos personales y contacto",
                iconoResId = 0, // Por ahora podemos poner 0 mientras no usemos un ícono real
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