package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.service

data class Teste(
    val id_teste_humor: Int,
    val paciente: String,
    val data: String,
    val horario: String,
    val observacao: String,
    val humores: List<Humor>,
    val sintomas: List<Sintoma>,
    val exercicios: List<Exercicio>
)