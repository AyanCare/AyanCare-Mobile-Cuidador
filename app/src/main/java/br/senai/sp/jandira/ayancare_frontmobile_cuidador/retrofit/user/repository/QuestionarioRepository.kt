package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository

import androidx.core.os.persistableBundleOf
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service.RelatorioService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class QuestionarioRepository {

    private val apiService = RetrofitFactory.getInstance().create(RelatorioService::class.java)

    suspend fun updateQuestionario(
        id:Int,
        resposta:Boolean
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("id", id)
            addProperty("resposta",resposta)
        }

        return apiService.updateQuestionario(requestBody)

    }

}