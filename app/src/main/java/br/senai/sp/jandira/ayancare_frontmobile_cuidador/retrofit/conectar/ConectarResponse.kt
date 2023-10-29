package br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service.Conectar

data class ConectarResponse(
    val status: Int,
    val cuidadores: List<Conectar>
)