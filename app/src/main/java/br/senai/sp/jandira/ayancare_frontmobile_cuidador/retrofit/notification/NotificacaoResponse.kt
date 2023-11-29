package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.notification

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.notification.service.Notificacao
import retrofit2.http.GET
import retrofit2.http.Query

data class NotificacaoResponse(
    val status: Int,
    val notificacao: List<Notificacao>
)