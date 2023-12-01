package br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.service

data class Event(
    val id: Int,
    val paciente: String,
    val cuidador: String,
    val nome: String,
    val descricao: String,
    val local: String,
    val horario: String,
    val dia: String,
    val dia_unico: String,
    val mes: String,
    val cor: String
)
