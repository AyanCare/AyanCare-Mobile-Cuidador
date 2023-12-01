package br.senai.sp.jandira.ayancare_frontmobile.retrofit.cor

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cor.service.Cor

data class CorResponse (
    val status: Int,
    val quantidade: Int,
    val cores: List<Cor>
)