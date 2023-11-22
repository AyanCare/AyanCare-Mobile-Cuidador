package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service

import android.provider.ContactsContract.CommonDataKinds.Email
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.genero.service.Genero

data class Cuidador(
    val id:Int,
    val nome:String,
    val email: String,
    val foto:String,
    val data_nascimento: String,
    val genero: String,
    val descricao_experiencia:String,
    val endereco_id:Int,
    val logradouro: String,
    val bairro: String,
    val  numero: Int,
    val cep: Int,
    val cidade: String,
    val estado: String

)
