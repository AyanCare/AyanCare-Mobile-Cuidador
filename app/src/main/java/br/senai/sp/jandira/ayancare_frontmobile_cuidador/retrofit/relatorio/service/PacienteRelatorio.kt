package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service

data class PacienteRelatorio(
    val id: Int,
    val foto: String,
    val nome: String,
    val data_nascimento: String,
    val idade: String,
    val genero: String
)
