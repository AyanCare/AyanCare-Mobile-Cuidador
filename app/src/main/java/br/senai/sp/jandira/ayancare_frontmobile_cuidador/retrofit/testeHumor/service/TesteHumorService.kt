package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.service

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.TesteHumorResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.TesteHumorResponseJSON
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TesteHumorService {

    @GET("/v1/ayan/testes")
    fun getTesteHumorByIdPaciente(@Query("idPaciente") idPaciente: Int): retrofit2.Call<TesteHumorResponse>

    @GET("/v1/ayan/teste/{id}")
    fun getTesteHumorById(@Path("id") id: Int): retrofit2.Call<TesteHumorResponseJSON>

}