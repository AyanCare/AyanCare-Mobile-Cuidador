package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.finalizarCadastro.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.genero.GeneroResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.genero.service.Genero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownGender(
    context: Context,
    gender: String,
    onValueChange: (String) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var listGeneros by remember {
        mutableStateOf<List<Genero>>(emptyList())
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getGenero().getGenero()

    call.enqueue(object : Callback<GeneroResponse> {
        override fun onResponse(
            call: Call<GeneroResponse>,
            response: Response<GeneroResponse>
        ) {
            listGeneros = response.body()!!.pacientes
        }
        override fun onFailure(call: Call<GeneroResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })

    var gender = gender



    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it}
        ) {

            TextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                listGeneros.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.nome, color = Color.Black) },
                        onClick = {
                            gender = it.nome // Atualiza a variável com a seleção do usuário
                            onValueChange(it.nome) // Chama a função de retorno com o valor selecionado
                            isExpanded = false
                        }
                    )
                }
            }

        }
    }

}