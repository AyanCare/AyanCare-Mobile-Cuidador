package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service.RelatorioService
import com.google.gson.JsonObject
import retrofit2.Call

class RelatorioRepository {

    private val apiService = RetrofitFactory.getInstance().create(RelatorioService::class.java)

    suspend fun registerRelatorio(
        texto_relatorio: String,
        validacao: Int,
        id_paciente: Int,
        id_cuidador: Int
    ): Call<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("texto_relatorio", texto_relatorio)
            addProperty("validacao", validacao)
            addProperty("id_paciente", id_paciente)
            addProperty("id_cuidador", id_cuidador)
        }

        return apiService.createRelatorio(requestBody)

    }

}