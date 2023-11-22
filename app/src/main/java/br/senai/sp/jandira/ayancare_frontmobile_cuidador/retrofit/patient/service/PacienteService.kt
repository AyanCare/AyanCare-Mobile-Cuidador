package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PacienteResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface PacienteService {

    @GET("/v1/ayan/paciente/{id}")
    fun getPatientById(@Header("x-access-token")token:String,@Path("id") id: String?): retrofit2.Call<PacienteResponse>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/doenca")
    suspend fun createChronicDiseases(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/comorbidade")
    suspend fun createComorbidity(@Body body: JsonObject): Response<JsonObject>

}