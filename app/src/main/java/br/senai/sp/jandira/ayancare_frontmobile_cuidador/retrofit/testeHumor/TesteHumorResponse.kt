package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.service.Teste

data class TesteHumorResponse(
    val status: Int,
    val testes: List<Teste>,
)
