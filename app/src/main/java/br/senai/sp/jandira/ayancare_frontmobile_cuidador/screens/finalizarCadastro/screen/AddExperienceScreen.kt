package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.finalizarCadastro.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.TextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.CadastroRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.cadastro.components.ProgressBar
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.funcaoQueChamaSqlLite.deleteUserSQLite
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.funcaoQueChamaSqlLite.saveLogin
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import kotlinx.coroutines.launch

@Composable
fun AddExperienceScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    localStorage: Storage
) {

    val context = LocalContext.current
    var experienciaState by remember {
        mutableStateOf("") }

    val senha = localStorage.lerValor(context, "senha_paciente")
    val array = CuidadorRepository(context = context).findUsers()

    val cuidador = array[0]
    var id = cuidador.id.toLong()
    var token = cuidador.token
    var nome = cuidador.nome
    var data_nascimento = cuidador.dataNascimento
    var email = cuidador.email
    var foto = cuidador.foto
    var genero = cuidador.genero

    fun atualizarDados(
        token: String,
        id: Int,
        nome: String,
        data_nascimento: String,
        email: String,
        senha:String,
        foto: String,
        experiencia:String,
        id_endereco_cuidador: Int,
        genero: String
    ) {
        val userRepository = CadastroRepository()
        lifecycleScope.launch {

            val response = userRepository.updateUser(
                token,
                id,
                nome,
                data_nascimento,
                email,
                senha,
                foto,
                experiencia,
                id_endereco_cuidador,
                genero

            )

            Log.e("response", "finalizarCadastro: $response")

            if (response.isSuccessful) {
                deleteUserSQLite(context, id)
                saveLogin(
                    context = context,
                    token = token!!,
                    id = id.toLong(),
                    nome = nome,
                    dataNascimento = data_nascimento,
                    email = email,
                    foto = foto,
                    experiencia = experiencia,
                    genero = genero,
                    tipo = "Cuidador"
                )

                Log.d(MainActivity::class.java.simpleName, "Registro bem-sucedido")

                navController.navigate("main_screen")

            } else {

                val errorBody = response.errorBody()?.string()
                Log.e(MainActivity::class.java.simpleName, "Erro durante o registro: $errorBody")
                Toast.makeText(context, "Erro durante o registro", Toast.LENGTH_SHORT).show()

            }
        }
    }

    Surface(
        color = Color(248, 240, 236)
    ) {
        Wave()
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Adicione sua Experiência",
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Descreva sua experiência com algum idoso.",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9E8BC1),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(40.dp))

                TextField(
                    value = experienciaState,
                    onValueChange = { experienciaState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
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
            Column(
                modifier = Modifier.width(190.dp)
            ) {
                DefaultButton(
                    text = "Finalizar",
                    onClick = {

                        Log.i("TAG", "AddExperienceScreen: $token ")
                        Log.i("TAG", "AddExperienceScreen: $id ")
                        Log.i("TAG", "AddExperienceScreen: $nome ")
                        Log.i("TAG", "AddExperienceScreen: $data_nascimento ")
                        Log.i("TAG", "AddExperienceScreen: $email ")
                        Log.i("TAG", "AddExperienceScreen: $senha ")
                        Log.i("TAG", "AddExperienceScreen: $foto ")
                        Log.i("TAG", "AddExperienceScreen: $experienciaState ")
                        Log.i("TAG", "AddExperienceScreen: $genero ")

                        atualizarDados(
                            token = token.toString(),
                            id = id.toInt(),
                            nome = nome.toString(),
                            data_nascimento = data_nascimento.toString(),
                            email = email.toString(),
                            senha = senha.toString(),
                            foto = foto, // Use a imageURL
                            experiencia = experienciaState,
                            id_endereco_cuidador = 1,
                            genero = genero
                        )
                        navController.navigate("tela_instrucao1_screen")
                    }
                )
            }
            ProgressBar(text = "3 / 3", valor = 330)
        }
    }
}