package br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.funcaoQueChamaSqlLite

import android.content.Context
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.criacaoTabela.Cuidador
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository

fun saveLogin (
    context: Context,
    id: Long,
    token: String,
    nome: String,
    email: String,
    dataNascimento: String,
    foto:String,
    experiencia:String,
    genero: String,
    tipo: String
) : Long {

    val newPaciente = Cuidador(
        id = id,
        token = token,
        nome = nome,
        email = email,
        dataNascimento = dataNascimento,
        foto = foto,
        descricaoExperiencia = experiencia,
        genero = genero,
        tipo_usuario = tipo
    )

    return CuidadorRepository(context).save(newPaciente)
}
fun deleteUserSQLite(context: Context, id: Int): Int {
    return CuidadorRepository(context).deleteUser()
}