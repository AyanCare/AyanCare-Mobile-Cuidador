package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.service.UserService
import com.google.gson.JsonObject
import retrofit2.Response

class AlarmeRepository {

    private val apiService = RetrofitFactory.getInstance().create(UserService::class.java)

    suspend fun updateAlarmeUnitario(
        id: Int,
        id_status_alarme: Int
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("id", id)
            addProperty("id_status_alarme", id_status_alarme)
        }
        return apiService.updateAlarmeUnitario(requestBody)
    }

}
