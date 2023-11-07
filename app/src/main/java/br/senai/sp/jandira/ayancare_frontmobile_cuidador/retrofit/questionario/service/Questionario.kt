package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.service

data class Questionario(
    val id: Int,
    val pergunta: String,
    val resposta: Boolean,
    val id_relatorio: Int
)
