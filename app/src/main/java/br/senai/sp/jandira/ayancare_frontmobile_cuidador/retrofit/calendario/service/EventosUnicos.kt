package br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service

data class EventosUnicos(
    val id: Int,
    val id_paciente: Int,
    val paciente: String,
    val id_cuidador: Int,
    val cuidador: String,
    val nome: String,
    val descricao: String,
    val local: String,
    val dia: String,
    val dia_unico: String,
    val mes: String,
    val horario: String,
    val cor: String
)