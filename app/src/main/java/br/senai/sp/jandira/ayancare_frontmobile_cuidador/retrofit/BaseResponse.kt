package br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    var data: T? = null
)