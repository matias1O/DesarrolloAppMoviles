package com.duoc.TecnicoQCC.repository


import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PerfilRepositoryTest : StringSpec({

    "el nombre del perfil es el codigo de usuario" {
        val repository = PerfilRepository()

        val perfil = repository.obtenerPerfilUsuario("abc123")

        perfil.nombre shouldBe "abc123"
    }

    "las iniciales del avatar son las dos primeras letras en mayuscula" {
        val repository = PerfilRepository()

        val perfil = repository.obtenerPerfilUsuario("abc123")

            perfil.inicialesAvatar shouldBe "AB"
    }

    "el cargo del perfil es Inspector tecnico" {
        val respository = PerfilRepository()

        val perfil = respository.obtenerPerfilUsuario("abc123")

        perfil.cargo shouldBe "Inspector técnico"
    }

    "la empresa es CMC360 SPA" {

        val repository = PerfilRepository()

        val perfil = repository.obtenerPerfilUsuario("abc123")

        perfil.empresa shouldBe "CMC360 SPA"
    }

})