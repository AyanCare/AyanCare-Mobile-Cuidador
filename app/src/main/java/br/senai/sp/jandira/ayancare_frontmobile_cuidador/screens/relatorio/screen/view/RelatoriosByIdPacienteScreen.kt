package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.RelatorioResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service.CuidadorRelatorio
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service.PacienteRelatorio
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.service.Relatorio
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.components.CardRelatorio
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.components.FloatingActionButtonRelatorio
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RelatoriosByIdPacienteScreen(
    navController: NavController,
    localStorage: Storage
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var selectedDrop by remember { mutableStateOf("") }
    var pacienteId by remember { mutableStateOf(0) }

    var listRelatorio by remember {
        mutableStateOf(
            listOf(
                Relatorio(
                    0,
                    CuidadorRelatorio(
                        0,
                        "",
                        "",
                        "",
                        "",
                        ""
                    ),
                    PacienteRelatorio(
                        0,
                        ""
                        , "",
                        "",
                        "",
                        ""
                    ),
                    "",
                    "",
                    "",
                    emptyList()
                )
            )
        )
    }

    var id_paciente = localStorage.lerValor(context, "id_paciente_relatorio")
    Log.i("id_paciente_relatorio", "Calendary: $id_paciente")

    val array = CuidadorRepository(context = LocalContext.current).findUsers()
    val cuidador = array[0]
    val id_cuidador = cuidador.id.toLong()

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getRelatorio().getRelatorioByIdPacienteIdCuidador(id_paciente!!.toInt(), id_cuidador.toInt())

    call.enqueue(object : Callback<RelatorioResponse> {
        override fun onResponse(
            call: Call<RelatorioResponse>,
            response: Response<RelatorioResponse>
        ) {
            Log.e("TAG", "onResponse: ${response.body()}")
            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                listRelatorio = emptyList()
            } else {
                Log.e("TAG", "${response.body()!!.relatorio}")
                listRelatorio = response.body()!!.relatorio
            }
        }

        override fun onFailure(call: Call<RelatorioResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }
    })

    Surface(
        color = Color(248, 240, 236)
    ) {
        IconButton(
            onClick = {
                navController.navigate("main_screen")
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "",
                tint = Color.Black
            )
        }
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Relatório",
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFF35225F)
            )
            Spacer(modifier = Modifier.height(25.dp))
//            OutlinedTextField(
//                value = "",
//                onValueChange = {},
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = ""
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 15.dp, end = 15.dp)
//            )
//            DropdownPaciente(
//                context = context,
//                gender = selectedDrop ,
//                onValueChange = {
//                    selectedDrop = it
//                },
//                onPacienteSelected = { paciente ->
//                    pacienteId = paciente.id_paciente // Atualiza o ID do paciente na tela pai
//                }
//            )
            Spacer(modifier = Modifier.height(25.dp))
            if (listRelatorio.isEmpty()){
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = Icons.Default.Description,
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Text(
                        text = "Não existe relátorios deste paciente.",
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }
            }else{
                LazyColumn() {
                    items(listRelatorio) {
                        CardRelatorio(
                            text = it.texto,
                            data = it.data,
                            horario = it.horario,
                            onClick = {
                                localStorage.salvarValor(context, it.id.toString(), "id_relatorio")
                                localStorage.salvarValor(context, it.texto, "descricao_relatorio")
                                localStorage.salvarValor(context, it.paciente.id.toString(), "id_paciente_relatorio")
                                localStorage.salvarValor(context, it.paciente.nome, "nome_paciente_relatorio")
                                localStorage.salvarValor(context, it.paciente.idade, "idade_paciente_relatorio")
                                localStorage.salvarValor(context, it.paciente.genero, "genero_paciente_relatorio")
                                navController.navigate("relatorio_paciente_screen")
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }


        }
        FloatingActionButtonRelatorio(navController)
    }
}