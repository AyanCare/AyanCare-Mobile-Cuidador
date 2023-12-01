package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.ConectarResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service.Conectar
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ResearchField(
   navController: NavController,
    localStorage: Storage,
   navRotasController: NavController
) {
    val context = LocalContext.current
    var isDialogVisibleConect by remember { mutableStateOf(false) }
    var pacientes by remember { mutableStateOf("") }




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

    val array = CuidadorRepository(context = LocalContext.current).findUsers()
    val paciente = array[0]
    val id = paciente.id.toLong()

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
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .border(
                    width = 1.8.dp,
                    color = Color(167, 165, 164),
                    shape = RoundedCornerShape(5.dp)
                ),
            value = pacientes,
            onValueChange = { pacientes = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { /* Handle icon click if needed */ }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "search",
                        tint = Color.Black
                    )
                }
            }
        )
        
        Spacer(modifier = Modifier.height(15.dp))

        // Exibir o card do paciente correspondente ao nome digitado
        val filteredList = listPacientes.filter {
            it.paciente.lowercase().contains(pacientes.lowercase())
        }

        if (pacientes.isNotEmpty()) {
            Spacer(modifier = Modifier.height(15.dp))
            if (filteredList.isEmpty()) {
                Text("Paciente não encontrado...")
            } else {
                filteredList.firstOrNull()?.let { paciente ->
                    CardPaciente(
                        onUnlinkClick = {
                            navRotasController.navigate("relatorios_screen")
                        },
                        onProfileClick = {
                            navRotasController.navigate("Calendar_screen")
                        },
                        nome = paciente.paciente,
                        id = paciente.id_paciente,
                        foto = paciente.foto_paciente
                    )
                }
            }
        } else {
            LazyColumn() {
                items(listPacientes) { paciente ->
                    CardPaciente(
                        onUnlinkClick = {
                            isDialogVisibleConect = true
                            navRotasController.navigate("relatorios_screen")
                        },
                        onProfileClick = {
                            localStorage.salvarValor(context, listPacientes[0].id_paciente.toString(), "id_paciente")
                            navRotasController.navigate("Calendar_screen")
                        },
                        nome = paciente.paciente,
                        id = paciente.id_paciente,
                        foto = paciente.foto_paciente
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }
    }
}
