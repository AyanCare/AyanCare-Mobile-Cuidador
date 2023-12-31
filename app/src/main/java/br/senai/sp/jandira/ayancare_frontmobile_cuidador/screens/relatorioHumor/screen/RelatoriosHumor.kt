package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorioHumor.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.TesteHumorResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.service.Teste
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.Screen.EventScreen.components.DropdownPaciente
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.components.CardRelatorio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RelatoriosHumorScreen(
    navController: NavController,
    localStorage: Storage
) {
    val context = LocalContext.current

    var selectedDrop by remember { mutableStateOf("") }
    var pacienteId by remember { mutableStateOf(0) }


    var listTesteHumor by remember {
        mutableStateOf(
            listOf(
                Teste(
                    id_teste_humor = 0,
                    paciente = "",
                    data = "",
                    horario = "",
                    observacao = "",
                    humores = emptyList(),
                    sintomas = emptyList(),
                    exercicios = emptyList()
                )
            )
        )
    }

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
                text = "Relatórios de Humor",
                fontSize = 30.sp,
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
            DropdownPaciente(
                context = context,
                gender = selectedDrop ,
                onValueChange = {
                    selectedDrop = it
                },
                onPacienteSelected = { paciente ->
                    pacienteId = paciente.id_paciente // Atualiza o ID do paciente na tela pai

                    //Cria uma chamada para o endpoint
                    var call = RetrofitFactory.getTesteHumor().getTesteHumorByIdPaciente(pacienteId)

                    call.enqueue(object : Callback<TesteHumorResponse> {
                        override fun onResponse(
                            call: Call<TesteHumorResponse>,
                            response: Response<TesteHumorResponse>
                        ) {
                            //Log.e("TAG", "onResponse: ${response.body()}")
                            if (response.body()!!.status == 404) {
                                //Log.e("TAG", "a resposta está nula")
                                listTesteHumor = emptyList()
                            } else {
                                //Log.e("TAG", "${response.body()!!.testes}")
                                listTesteHumor = response.body()!!.testes
                            }
                            //Log.e("TAG", "onResponse: $listCor")
                        }

                        override fun onFailure(call: Call<TesteHumorResponse>, t: Throwable) {
                            Log.i("ds3t", "onFailure: ${t.message}")
                        }
                    })
                }
            )
            Spacer(modifier = Modifier.height(25.dp))
            if (listTesteHumor.isEmpty()){
                //Text(text = "nao a relatorios")
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = Icons.Default.Description,
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Text(
                        text = "Não existe relátorios de humor deste paciente.",
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }
            }else if(selectedDrop == ""){
                //Text(text = "Escolha um Paciente")
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Text(
                        text = "Escolha um Paciente",
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }
            } else{
                LazyColumn() {
                    items(listTesteHumor) {
                        CardRelatorio(
                            text = it.observacao,
                            data = it.data,
                            horario = it.horario,
                            onClick = {
                                localStorage.salvarValor(context, it.id_teste_humor.toString(), "id_teste_humor")
                                navController.navigate("relatorio_humor_screen")
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}