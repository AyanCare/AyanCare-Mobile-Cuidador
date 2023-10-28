package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.service.UserService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import com.google.gson.JsonObject
import retrofit2.Response

class LoginRepository {

    private val apiService = RetrofitFactory.getInstance().create(UserService::class.java)

    suspend fun loginUser(email: String, senha: String): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("email", email)
            addProperty("senha", senha)
        }

        return apiService.postUser(requestBody)

    }
}