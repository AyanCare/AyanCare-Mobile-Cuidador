package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service.Relatorio

data class RelatorioResponse(
    val status: Int,
    val relatorio: List<Relatorio>
)
