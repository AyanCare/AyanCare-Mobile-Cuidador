package br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service

data class Alarmes(
    val id: Int,
    val id_paciente: Int,
    val paciente: String,
    val id_medicamento: Int,
    val medicamento: String,
    val horario: String,
    val intervalo: String,
    val quantidade: Int,
    val medida: String,
    val medida_sigla: String,
    val id_status: Int,
    val status: String
)
