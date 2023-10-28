package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.genero.service.GeneroService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    //private const val BASE_URL = "http://10.107.144.22:8080"
    //private const val BASE_URL = "https://ayancare-api.cyclic.cloud"
    private const val BASE_URL = "http://192.168.0.115:8080" //192.168.0.120
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

//    fun getPatient(): PacienteService {
//        return retrofitFactory.create(PacienteService::class.java)
//    }

    fun getGenero(): GeneroService {
        return retrofitFactory.create(GeneroService::class.java)
    }


}