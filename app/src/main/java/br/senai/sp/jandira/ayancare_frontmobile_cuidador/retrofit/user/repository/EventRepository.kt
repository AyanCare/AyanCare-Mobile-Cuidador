package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.service.EventService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import com.google.gson.JsonArray
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


    suspend fun registerEventSemanal(
        nome: String,
        descricao: String,
        local: String,
        hora: String,
        id_paciente_cuidador: Int,
        dias: List<String>,
        cor_id: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome", nome)
            addProperty("descricao", descricao)
            addProperty("local", local)
            addProperty("hora", hora)
            addProperty("id_paciente_cuidador", id_paciente_cuidador)

            // Criar um JsonArray para as escolhas
            val escolhasArray = JsonArray()
            dias.forEach { escolha ->
                escolhasArray.add(escolha)
            }
            add("dias", escolhasArray)

            addProperty("cor_id", cor_id)
        }
        return apiService.createEvent(requestBody)
    }

}
//{
//    "nome": "Teste de evento semanal",
//    "descricao": "Descricao aí ó",
//    "local": "Lá naquele lugar lá",
//    "hora": "17:00",
//    "id_paciente_cuidador": 16,
//    "dias": ["domingo", "sexta"],
//    "cor_id": 4
//}
