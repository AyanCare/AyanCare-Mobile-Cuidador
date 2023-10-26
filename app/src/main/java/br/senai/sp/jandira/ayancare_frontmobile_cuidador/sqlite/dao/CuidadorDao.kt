package br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.criacaoTabela.Cuidador

@Dao
interface CuidadorDao {

    @Insert
    fun save(cuidador: Cuidador): Long

    @Update
    fun update(cuidador: Cuidador): Int

    @Query("SELECT * FROM tbl_cuidador")
    fun findUsers(): List<Cuidador>

    @Query("DELETE FROM tbl_cuidador")
    fun deleteAll(): Int
}