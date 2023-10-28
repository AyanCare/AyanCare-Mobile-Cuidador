package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.service.UserService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import com.google.gson.JsonObject
import retrofit2.Response

class ResetPasswordRepository {

    private val apiService = RetrofitFactory.getInstance().create(UserService::class.java)

    suspend fun requestPasswordReset(email: String): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("email", email)
        }

        return apiService.requestPasswordReset(requestBody)
    }

}