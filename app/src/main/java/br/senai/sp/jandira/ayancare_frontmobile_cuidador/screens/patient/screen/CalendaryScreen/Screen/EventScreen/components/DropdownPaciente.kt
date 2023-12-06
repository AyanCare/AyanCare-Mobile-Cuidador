package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.Screen.EventScreen.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.ConectarResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service.Conectar
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownPaciente(
    context: Context,
    gender: String,
    onValueChange: (String) -> Unit,
    onPacienteSelected: (Conectar) -> Unit
) {

    val array = CuidadorRepository(context = context).findUsers()

    val cuidador = array[0]
    var id = cuidador.id.toLong()

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var selectedPaciente by remember { mutableStateOf(Conectar(0, "", 0, "", "", 0, "")) }

    var listPacientes by remember {
        mutableStateOf(
            listOf(
                Conectar(
                    0,
                    "",
                    0,
                    "",
                    "",
                    0,
                    ""
                )
            )
        )
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getConectar().getConectar(id.toInt())

    call.enqueue(object : Callback<ConectarResponse> {
        override fun onResponse(
            call: Call<ConectarResponse>,
            response: Response<ConectarResponse>
        ) {
            Log.i("teste de conexao", "onResponse: ${response.body()}")
            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                listPacientes = emptyList()
            } else {
                listPacientes = response.body()!!.conexao
            }
        }

        override fun onFailure(call: Call<ConectarResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })

    var gender = gender

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
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
                listPacientes.forEach { paciente ->
                    DropdownMenuItem(
                        text = { Text(text = paciente.paciente, color = Color.Black) },
                        onClick = {
                            selectedPaciente = paciente // Atualiza o paciente selecionado
                            gender = paciente.paciente // Atualiza a variável com a seleção do usuário
                            onValueChange(paciente.paciente) // Chama a função de retorno com o valor selecionado
                            onPacienteSelected(paciente)
                            isExpanded = false
                        }
                    )
                }
            }
//            if (selectedPaciente.id != 0) {
//                Column(
//                    modifier = Modifier.padding(16.dp),
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = selectedPaciente.paciente,
//                        fontSize = 25.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
//                        fontWeight = FontWeight(600),
//                        color = Color(0xFF35225F)
//                    )
//                    Text(
//                        text = "#${selectedPaciente.id_paciente}",
//                        fontSize = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
//                        fontWeight = FontWeight(500),
//                        color = Color(0xFF9986BD)
//                    )
//                }
//            }

        }

    }
}
