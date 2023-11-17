package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Cuidador

data class CuidadorResponse(
    val status: Int,
    val cuidador: Cuidador
)
