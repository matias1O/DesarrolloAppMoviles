package com.duoc.TecnicoQCC.viewmodel

import com.duoc.TecnicoQCC.model.Rack
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RackViewModelTest : StringSpec ({

    "al crear el viewmodel la lista tiene 1 rack inicial" {
        val viewModel = RackViewModel()

        viewModel.racks.size shouldBe 1
    }


    "agregarRack agrega el nuevo rack al principio de la lista" {

        val viewModel = RackViewModel()
        val nuevoRack = Rack(
            id = "RACK-002",
            nombre = "Sala secundaria",
            area = "Zona B",
            fecha = "20 mar 2026",
            inspector = "Matias",
            estado = "Pendiente"
        )

        viewModel.agregarRack(nuevoRack)

        viewModel.racks.size shouldBe 2
        viewModel.racks[0].id shouldBe "RACK-002"
    }
})