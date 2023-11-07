package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.service

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.QuestionarioResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionarioService {

    @GET("/v1/ayan/questionarios/")
    fun getQuestionarioByIdRelatorio(@Query("idRelatorio") idRelatorio: Int): retrofit2.Call<QuestionarioResponse>

}