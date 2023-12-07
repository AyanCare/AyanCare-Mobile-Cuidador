package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.view


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.RadioButton
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
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.QuestionarioResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.service.Questionario
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.relatorio.RelatorioResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ViewQuestionScreen(
    navController: NavController,
    localStorage: Storage
) {

    val context = LocalContext.current

    var listQuestionario by remember {
        mutableStateOf(
            listOf(
                Questionario(0, "", false, 0)
            )
        )
    }

    var id_relatorio = localStorage.lerValor(context, "id_relatorio")
    Log.e("TAG", "ViewQuestionScreen: $id_relatorio", )

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getQuestionario().getQuestionarioByIdRelatorio(id_relatorio!!.toInt())

    call.enqueue(object : Callback<QuestionarioResponse> {
        override fun onResponse(
            call: Call<QuestionarioResponse>,
            response: Response<QuestionarioResponse>
        ) {
            Log.e("TAG", "onResponse: ${response.body()}")
            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                listQuestionario = emptyList()
            } else {
                Log.e("TAG", "${response.body()!!.questionario}")
                listQuestionario = response.body()!!.questionario
            }
        }

        override fun onFailure(call: Call<QuestionarioResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }
    })

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 20.dp)
                .fillMaxSize()
        ) {
//            Row (
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(end = 50.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//            ){
//                IconButton(
//                    onClick = {
//                        navController.navigate("relatorio_screen")
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.ArrowBackIosNew,
//                        contentDescription = ""
//                    )
//                }
//                Text(
//                    text = "Questionário",
//                    fontSize = 28.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins)),
//                    fontWeight = FontWeight(600),
//                    color = Color(0xFF35225F)
//                )
//            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.width(80.dp))
                Text(
                    text = "Questionário",
                    fontSize = 28.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp)
            ) {

                Spacer(modifier = Modifier.height(40.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(listQuestionario) {
                        Column (
                            modifier = Modifier.fillMaxSize()
                        ){
                            Text(
                                text = it.pergunta,
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF9986BD)
                            )
                            Row(
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (it.resposta){
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = true,
                                            onClick = {}
                                        )
                                        Text(
                                            text = "Sim",
                                            fontSize = 16.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins)),
                                            fontWeight = FontWeight(400),
                                            color = Color(0xFF35225F)
                                        )
                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = false,
                                            onClick = {}
                                        )
                                        Text(
                                            text = "Não",
                                            fontSize = 16.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins)),
                                            fontWeight = FontWeight(400),
                                            color = Color(0xFF35225F)
                                        )
                                    }
                                }else{
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = false,
                                            onClick = {}
                                        )
                                        Text(
                                            text = "Sim",
                                            fontSize = 16.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins)),
                                            fontWeight = FontWeight(400),
                                            color = Color(0xFF35225F)
                                        )
                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = true,
                                            onClick = {}
                                        )
                                        Text(
                                            text = "Não",
                                            fontSize = 16.sp,
                                            fontFamily = FontFamily(Font(R.font.poppins)),
                                            fontWeight = FontWeight(400),
                                            color = Color(0xFF35225F)
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}