package br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service

data class Calendario(
    val eventos_unicos: List<EventosUnicos>,
    val eventos_semanais: List<EventosSemanais>,
    val alarmes: List<Alarmes>,
)
