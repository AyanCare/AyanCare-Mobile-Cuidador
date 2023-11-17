package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.questionario.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FolderCopy
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.PerguntasRelatorioRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

@Composable
fun AddQuestionScreen(
    navController: NavController,
    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope,
) {

    var descricaoState by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val array = CuidadorRepository(context = context).findUsers()

    val cuidador = array[0]
    var id = cuidador.id.toLong()


    fun response(
        pergunta:String,
        id_cuidador:Int
    ){

        val perguntasRelatorioRepository= PerguntasRelatorioRepository()
        lifecycleScope.launch {
            var  response = perguntasRelatorioRepository.registerPergunta(
                pergunta,
                id_cuidador
            )
            if (response.isSuccessful){
                Log.e(MainActivity::class.java.simpleName, "Relatorio bem-sucedido")

                val checagem = response.body()?.get("status")

                if (checagem.toString() == "404"){

                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                }else{

                    val jsonString = response.body().toString()
                    val jsonObject = JSONObject(jsonString)
                    val relatorioObject = jsonObject.getJSONObject("pergunta")

                    val id = relatorioObject.getInt("id")
                    Log.i("id_pergunta", "response:$id ")

                    localStorage.salvarValor(context,id.toString(),"id_pergunta")




                    Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                    navController.navigate("question_screen")
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
            //verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.FolderCopy,
                contentDescription = "add question",
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Adicionar Pergunta",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = descricaoState,
                onValueChange = { descricaoState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF9986BD),
                        shape = RoundedCornerShape(4.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(248, 240, 236)
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                DefaultButton(
                    onClick = {
                            response(
                                descricaoState,
                                id.toInt()
                            )
                    },
                    text = "Salvar"
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Cancelar",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }

        }

    }
}
