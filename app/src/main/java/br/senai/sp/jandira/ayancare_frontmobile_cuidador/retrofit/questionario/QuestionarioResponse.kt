package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario

import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.service.Questionario

data class QuestionarioResponse(
    val status: Int,
    val questionario: List<Questionario>
)
