package br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository

import android.content.Context
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.dao.AyanCareDb
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.criacaoTabela.Cuidador

class CuidadorRepository(context: Context) {

    private val db = AyanCareDb.getDatabase(context)

    fun save(cuidador: Cuidador): Long {
        return db.cuidadorDao().save(cuidador)
    }

    fun update(cuidador: Cuidador): Long{
        return db.cuidadorDao().update(cuidador).toLong()
    }

    fun findUsers(): List<Cuidador> {
        return db.cuidadorDao().findUsers()
    }

    fun deleteUser(): Int{
        return  db.cuidadorDao().deleteAll()
    }
}