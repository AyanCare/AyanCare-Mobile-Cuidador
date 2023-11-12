package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.RelatorioResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RelatorioService {

    @GET("/v1/ayan/relatorios/")
    fun getRelatorioByIdPaciente(@Query("idPaciente") idPaciente: Int): retrofit2.Call<RelatorioResponse>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/conectar")
    suspend fun createRelatorio(@Body body: JsonObject): retrofit2.Response<JsonObject>

}