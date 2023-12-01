package br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service

data class EventosSemanais(
    val id: Int,
    val id_paciente: Int,
    val paciente: String,
    val id_cuidador: Int,
    val cuidador: String,
    val nome: String,
    val descricao: String,
    val local: String,
    val cor: String
)