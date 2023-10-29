package br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service


import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.ConectarResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ConectarService {

    @GET("/v1/ayan/paciente/conectados/{id}")
    fun getConectar(@Path("id") id: Int): retrofit2.Call<ConectarResponse>

    @POST("/v1/ayan/conectar")
    fun createConect(@Query("idPaciente") idPaciente: Int, @Query("idCuidador") idCuidador: Int): retrofit2.Call<ConectarResponse>

}