package br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service


import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.ConectarResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.conectar.service.AtivarContaResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.conectar.service.DesativarContaResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ConectarService {

    @GET("/v1/ayan/conexoes")
    fun getConectar(@Query("idCuidador") idCuidador: Int): retrofit2.Call<ConectarResponse>

    @POST("/v1/ayan/conectar")
    fun createConect(@Query("idCuidador") idCuidador: Int, @Query("idPaciente") idPaciente: Int): retrofit2.Call<ConectarResponse>

    @PUT("/v1/ayan/conexao/desativar")
    fun updateConectDesativar(@Query("idCuidador") idCuidador: Int, @Query("idPaciente") idPaciente: Int): retrofit2.Call<DesativarContaResponse>

    @PUT("/v1/ayan/conexao/ativar")
    fun updateConectAtivar(@Query("idCuidador") idCuidador:Int, @Query("idPaciente") idPaciente: Int): retrofit2.Call<AtivarContaResponse>

}