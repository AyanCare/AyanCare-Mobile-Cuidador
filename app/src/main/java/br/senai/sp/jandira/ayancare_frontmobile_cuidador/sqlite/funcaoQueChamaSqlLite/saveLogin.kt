package br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.funcaoQueChamaSqlLite

import android.content.Context
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.criacaoTabela.Cuidador
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository

fun saveLogin (
    context: Context,
    id: Long,
    nome: String,
    token: String,
    dataNascimento: String,
    genero: String,
    email: String,
    foto:String,
    tipo: String
) : Long {

    val newPaciente = Cuidador(
        id = id,
        nome = nome,
        token = token,
        email = email,
        dataNascimento = dataNascimento,
        genero = genero,
        foto = foto,
        tipo_usuario = tipo
    )

    return CuidadorRepository(context).save(newPaciente)
}
fun deleteUserSQLite(context: Context, id: Int): Int {
    return CuidadorRepository(context).deleteUser()
}