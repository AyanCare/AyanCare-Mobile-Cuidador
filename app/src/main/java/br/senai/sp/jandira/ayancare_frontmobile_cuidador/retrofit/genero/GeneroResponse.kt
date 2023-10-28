package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.genero

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.genero.service.Genero

data class GeneroResponse(
    val status: Int,
    val quantidade: Int,
    val pacientes: List<Genero>
)
