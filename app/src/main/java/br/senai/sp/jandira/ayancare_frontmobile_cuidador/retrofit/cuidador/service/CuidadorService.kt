package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.CuidadorResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface CuidadorService {

    @GET("/v1/ayan/cuidador/{id}")
    fun getCuidadorById(@Path("id") di:String):retrofit2.Call<CuidadorResponse>

}