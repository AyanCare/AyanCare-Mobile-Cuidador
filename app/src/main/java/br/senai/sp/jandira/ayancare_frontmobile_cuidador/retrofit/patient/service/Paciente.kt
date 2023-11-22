package br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.ComorbidadesList
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.DoencasCronicasList

data class Paciente(
    val id: Int,
    val nome: String,
    val data_nascimento: String,
    val email: String,
    val senha: String,
    val cpf: String,
    val foto: String,
    val historico_medico: String,
    val doencas_cronicas: List<DoencasCronicasList>,
    val comorbidades: List<ComorbidadesList>
)
