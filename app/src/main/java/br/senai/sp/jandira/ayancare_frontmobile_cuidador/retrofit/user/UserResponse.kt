package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") var id:Int? = 0,
    @SerializedName("nome") var nome: String = "",
    @SerializedName("descricao") var descricao: String = "",
    @SerializedName("foto") var foto: String = "",
    @SerializedName("nome_de_usuario") var nome_de_usuario: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("senha") var senha: String = ""
)
