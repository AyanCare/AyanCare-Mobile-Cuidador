package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.notification.service

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.notification.NotificacaoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NotificacaoService{
        @GET("/v1/ayan/notificacoes")
        fun getNotificacaobyIdCuidador(@Query("idCuidador") idCuidador: Int): retrofit2.Call<NotificacaoResponse>

}