package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository

import androidx.core.os.persistableBundleOf
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service.RelatorioService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class QuestionarioRepository {

    private val apiService = RetrofitFactory.getInstance().create(RelatorioService::class.java)

    suspend fun registerQuestionario(
        id_perguta:Int,
        id_relatorio: Int,
        resposta:Boolean
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {

            addProperty("id_pergunta", id_perguta)
            addProperty("id_relatorio", id_relatorio)
            addProperty("resposta",resposta)
        }

        return apiService.createRelatorio(requestBody)

    }

}