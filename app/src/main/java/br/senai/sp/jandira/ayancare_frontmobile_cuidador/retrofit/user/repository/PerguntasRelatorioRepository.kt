package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service.RelatorioService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class PerguntasRelatorioRepository {

    private val apiService = RetrofitFactory.getInstance().create(RelatorioService::class.java)

    suspend fun registerPergunta(
        pergunta:String,
        id_cuidador:Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("pergunta", pergunta)
            addProperty("id_cuidador", id_cuidador)
        }

        return apiService.createPergunta(requestBody)

    }
}