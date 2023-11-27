package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorioHumor.screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
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
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.TesteHumorResponseJSON
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.testeHumor.service.Teste
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorioHumor.components.CardMoodToday
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorioHumor.components.Exercise
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorioHumor.components.Symptoms
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RelatorioHumorScreen(
    navController: NavController,
    localStorage: Storage
) {
    val context = LocalContext.current

    var id_teste_humor = localStorage.lerValor(context, "id_teste_humor")

    var listTesteHumor by remember {
        mutableStateOf(
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
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getTesteHumor().getTesteHumorById(id_teste_humor!!.toInt())

    call.enqueue(object : Callback<TesteHumorResponseJSON> {
        override fun onResponse(
            call: Call<TesteHumorResponseJSON>,
            response: Response<TesteHumorResponseJSON>
        ) {
            Log.e("TAG", "onResponse: ${response.body()}")
            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
            } else {
                Log.e("TAG", "${response.body()!!.teste}")
                listTesteHumor = response.body()!!.teste
            }
        }
        override fun onFailure(call: Call<TesteHumorResponseJSON>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }
    })

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 20.dp)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("relatorios_humor_screen")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Relatório de Humor",
                    fontSize = 30.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Como você está hoje?",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow() {
                    items(listTesteHumor.humores) {
                        CardMoodToday(
                            text = it.nome,
                            foto = it.icone,
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }

                Spacer(modifier = Modifier.height(26.dp))

                Column {
                    Text(
                        text = "Praticou algum exercício?",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow() {
                        items(listTesteHumor.exercicios) {
                            Exercise(
                                text = it.nome,
                                foto = it.icone,
                                onClick = {}
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Sentiu algum sintoma?",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow() {
                    items(listTesteHumor.sintomas) {
                        Symptoms(
                            text = it.nome,
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column {
                    Text(
                        text = "Tem algo a acrescentar?",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF191D23)

                    )
                    OutlinedTextField(
                        value = listTesteHumor.observacao,
                        onValueChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .border(
                                width = 1.dp,
                                color = Color(100, 116, 139),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        enabled = false
                    )
                }
            }
        }
    }
}