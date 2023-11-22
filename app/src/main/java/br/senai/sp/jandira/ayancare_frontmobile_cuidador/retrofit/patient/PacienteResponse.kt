package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Paciente

data class PacienteResponse(
    val status: Int,
    val paciente: Paciente
)
