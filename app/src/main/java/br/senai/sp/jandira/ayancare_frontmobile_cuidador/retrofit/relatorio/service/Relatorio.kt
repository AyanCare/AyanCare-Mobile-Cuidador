package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service

data class Relatorio(
    val id: Int,
    val cuidador: CuidadorRelatorio,
    val paciente: PacienteRelatorio,
    val data: String,
    val horario: String,
    val texto: String,
    val perguntas: List<PerguntasRelatorio>
)
