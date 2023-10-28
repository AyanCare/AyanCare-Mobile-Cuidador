package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.genero.service

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.genero.GeneroResponse
import retrofit2.http.GET

interface GeneroService {

    @GET("/v1/ayan/generos")
    fun getGenero(): retrofit2.Call<GeneroResponse>

}