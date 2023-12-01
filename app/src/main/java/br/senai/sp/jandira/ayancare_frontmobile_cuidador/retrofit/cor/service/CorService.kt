package br.senai.sp.jandira.ayancare_frontmobile.retrofit.cor.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cor.CorResponse
import retrofit2.http.GET

interface CorService {

    @GET("/v1/ayan/cores")
    fun getCor(): retrofit2.Call<CorResponse>

}