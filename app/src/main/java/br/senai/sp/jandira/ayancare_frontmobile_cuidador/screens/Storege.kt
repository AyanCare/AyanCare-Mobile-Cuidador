package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens

import android.content.Context
import android.content.SharedPreferences

class Storage {
    fun salvarValor(context: Context, valor: String, nome: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("NomeDoArquivo", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(nome, valor)
        editor.apply()
    }

    fun lerValor(context: Context, nome: String): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("NomeDoArquivo", Context.MODE_PRIVATE)
        return sharedPreferences.getString(nome, null)
    }
}