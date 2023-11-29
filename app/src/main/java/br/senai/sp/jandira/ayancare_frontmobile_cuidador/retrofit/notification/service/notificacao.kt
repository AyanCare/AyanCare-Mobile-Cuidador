package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.notification.service

data class Notificacao(
    val id: Int,
    val nome: String,
    val descricao: String,
    val data_criacao: String,
    val hora_criacao: String,
    val id_paciente: String,
    val paciente: String
)