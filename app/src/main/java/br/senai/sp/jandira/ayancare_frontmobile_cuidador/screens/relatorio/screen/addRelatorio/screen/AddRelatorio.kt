@file:Suppress("NAME_SHADOWING")

package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.addRelatorio.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.RelatorioRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.Screen.EventScreen.components.DropdownPaciente
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

@Composable
fun AddRelatorioScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    localStorage: Storage
) {


    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var selectedDrop by remember { mutableStateOf("") }
    var pacienteId by remember { mutableStateOf(0) }

    val array = CuidadorRepository(context = context).findUsers()

    val cuidador = array[0]
    var id = cuidador.id.toLong()

    var descricaoState by remember {
        mutableStateOf("")
    }

    fun relatorio(
        texto_relatorio: String,
        validacao: Int,
        id_paciente: Int,
        id_cuidador: Int
    ) {
        val relatorioRepository = RelatorioRepository()
        lifecycleScope.launch {

            val response = relatorioRepository.registerRelatorio(
                texto_relatorio,
                validacao,
                id_paciente,
                id_cuidador

            )

            if (response.isSuccessful) {
                Log.e(MainActivity::class.java.simpleName, "Relatorio bem-sucedido")
                //Log.e("medicamento", "medicamento: ${response.body()}")
                val checagem = response.body()?.get("status")

                //Log.e("TAG", "medicamento: $id", )
                //Log.e("medicamento", "medicamento: ${checagem}")

                if (checagem.toString() == "404") {
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                } else {

                    val jsonString = response.body().toString()
                    val jsonObject = JSONObject(jsonString)
                    val relatorioObject = jsonObject.getJSONObject("relatorio")
                    Log.e("json String", "relatorio: $jsonString", )


                    val id = relatorioObject.getInt("id")
                    Log.i("teste", "relatorio: $id")

                    localStorage.salvarValor(context,id.toString(),"id_relatorio_questionario")

                    Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                    navController.navigate("question_screen")
                }
            } else {
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
                .padding(top = 20.dp, start = 15.dp, end = 15.dp)
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
                //horizontalAlignment = Alignment.CenterHorizontally,
                //verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DropdownPaciente(
                    context = context,
                    gender = selectedDrop ,
                    onValueChange = {
                        selectedDrop = it
                    },
                    onPacienteSelected = { paciente ->
                        pacienteId = paciente.id_paciente // Atualiza o ID do paciente na tela pai
                        // Aqui você pode fazer o que precisa com o ID do paciente na mesma tela
                    }
                )
                Text(
                    text = "$selectedDrop",
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F)
                )
                Text(
                    text = "#$pacienteId",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9986BD)
                )
//                Text(
//                    text = "84 anos, Mulher",
//                    fontSize = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins)),
//                    fontWeight = FontWeight(500),
//                    color = Color(0xFF9986BD)
//                )

                Spacer(modifier = Modifier.height(30.dp))

                Column {
                    Text(
                        text = "Relátorio",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF35225F)
                    )
                    TextField(
                        value = descricaoState,
                        onValueChange = { descricaoState = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .border(
                                width = 1.dp,
                                color = Color(167, 165, 164),
                                shape = RoundedCornerShape(4.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color(248, 240, 236)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DefaultButton(
                        onClick = {
                            relatorio(
                                "$descricaoState",
                                1,
                                pacienteId,
                                id.toInt()

                                )
                        },
                        text = "Questionário"
                    )
                }
            }
        }
    }
}
