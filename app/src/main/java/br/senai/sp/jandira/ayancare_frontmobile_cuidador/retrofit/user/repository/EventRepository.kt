package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.service.EventService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import com.google.gson.JsonObject
import retrofit2.Response

class EventRepository {

    private val apiService = RetrofitFactory.getInstance().create(EventService::class.java)

    suspend fun registerEvent(
        nome: String,
        descricao: String,
        local: String,
        hora: String,
        dia: String,
        idPaciente: Int,
        idCor: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome", nome)
            addProperty("descricao", descricao)
            addProperty("local", local)
            addProperty("hora", hora)
            addProperty("dia", dia)
            addProperty("idPaciente", idPaciente)
            addProperty("id_cor", idCor)
        }
        return apiService.createEvent(requestBody)
    }
}

