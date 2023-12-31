package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.questionario.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.QuestionarioResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.service.Questionario
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.QuestionarioRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun QuestionScreen(
    navController: NavController,
    //navRotasController: NavController
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope,
) {

    val context = LocalContext.current

    val id_relatorio = localStorage.lerValor(context,"id_relatorio_questionario")

    val id_pergunta = localStorage.lerValor(context,"id_pergunta")

    Log.d("id","${id_relatorio}")

    Log.d("id","${id_pergunta}")

    var listQuestionario by remember {
        mutableStateOf(
            listOf(
                Questionario(0, "", false, 0)
            )
        )
    }


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


    @SuppressLint("SuspiciousIndentation")
    fun response(
        id:Int,
        resposta:Boolean
    ){
        val questionarioRepository= QuestionarioRepository()
            lifecycleScope.launch {

                val response = questionarioRepository.updateQuestionario(
                    id,
                    resposta
                )
                if (response.isSuccessful){
                    Log.e(MainActivity::class.java.simpleName, "Relatorio bem-sucedido")

                    val checagem = response.body()?.get("status")

                    if (checagem.toString() == "404"){

                        Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                    }else{
                        //Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                        //navController.navigate("add_question_screen")
                    }
                }else{
                    val errorBody = response.errorBody()?.string()

                    Log.e(MainActivity::class.java.simpleName, "Erro durante o Relatorio: $errorBody")
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_SHORT).show()
                }
            }
    }
    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .fillMaxSize()
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 40.dp)
            ) {
                Text(
                    text = "Questionário ",
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F)
                )
                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    modifier = Modifier.height(500.dp)
                ) {
                    items(listQuestionario) {
                        var selectedOption = remember { mutableStateOf(false) }
                        Column {
                            Text(
                                text = "${it.pergunta}",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF9986BD)
                            )
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ){
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = selectedOption.value,
                                            onClick = {
                                                selectedOption.value = true
                                                Log.i("TAG", "QuestionScreen: ${it.id} = ${selectedOption.value}")
                                                response(it.id, selectedOption.value)
                                            }
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
                                            selected = !selectedOption.value,
                                            onClick = {
                                                selectedOption.value = false
                                                Log.i("TAG", "QuestionScreen: ${it.id} = ${selectedOption.value}")
                                                response(it.id, selectedOption.value)
                                            }
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

                Spacer(modifier = Modifier.height(20.dp))

                DefaultButton(
                    onClick = {
                        Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                        navController.navigate("main_screen")
                    },
                    text = "Salvar"
                )
            }
        }
    }
}

