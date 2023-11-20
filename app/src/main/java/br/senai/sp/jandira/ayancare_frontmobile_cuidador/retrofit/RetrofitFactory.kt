package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service.ConectarService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.CuidadorService
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.PacienteService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.genero.service.GeneroService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.service.QuestionarioService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service.RelatorioService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.service.TesteHumorService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    //private const val BASE_URL = "http://10.107.144.17:8080"
    private const val BASE_URL = "https://ayancare-api.cyclic.cloud"
    //private const val BASE_URL = "http://192.168.0.117:8080" //192.168.0.120
    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private var retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getGenero(): GeneroService {
        return retrofitFactory.create(GeneroService::class.java)
    }

    fun getConectar(): ConectarService {
        return retrofitFactory.create(ConectarService::class.java)
    }

    fun getPatient(): PacienteService {
        return retrofitFactory.create(PacienteService::class.java)
    }

    fun getCuidador(): CuidadorService {
        return retrofitFactory.create(CuidadorService::class.java)
    }

    fun getRelatorio(): RelatorioService {
        return retrofitFactory.create(RelatorioService::class.java)
    }

    fun getTesteHumor(): TesteHumorService {
        return retrofitFactory.create(TesteHumorService::class.java)
    }
    fun getQuestionario(): QuestionarioService {
        return retrofitFactory.create(QuestionarioService::class.java)
    }


}