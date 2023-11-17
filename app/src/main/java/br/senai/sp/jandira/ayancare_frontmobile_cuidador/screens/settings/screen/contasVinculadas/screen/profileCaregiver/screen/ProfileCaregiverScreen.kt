package br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.contasVinculadas.screen.profileCaregiver.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cuidador.CuidadorResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cuidador.service.Cuidador
import br.senai.sp.jandira.ayancare_frontmobile.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.components.CircleProfile
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.contasVinculadas.screen.profileCaregiver.components.CardTask
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileCaregiverScreen(
    navController: NavController,
    //navRotasController: NavController,
    localStorage: Storage
) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var id = localStorage.lerValor(context, "id_cuidador_conexao")
    val token = localStorage.lerValor(context, "token_paciente")

    Log.e("TAG", "ProfileCaregiverScreen: $id")

    var listCuidadores by remember {
        mutableStateOf(
            Paciente(

            )
        )
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getCuidador().getCuidadorByID(token = token.toString(), id = id)

    call.enqueue(object : Callback<CuidadorResponse> {
        override fun onResponse(
            call: Call<CuidadorResponse>,
            response: Response<CuidadorResponse>
        ) {
            listCuidadores = response.body()!!.cuidador
        }

        override fun onFailure(call: Call<CuidadorResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })

    Surface(
        color = Color(248, 240, 236)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            BoxProfile()
            IconButton(
                onClick = {
                    navController.navigate("setting_screen")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Column(
                //verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 110.dp, start = 15.dp, end = 15.dp, bottom = 20.dp)
                    .fillMaxSize()
            ) {
                CircleProfile(
                    painter = "painterResource(id = R.drawable.instrucao3)"
                )

                Text(
                    text = listCuidadores.nome,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000)
                )

                Text(
                    text = "Cuidador",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = listCuidadores.descricao_experiencia,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF9986BD),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Tarefas de Hoje",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F)
                )
                Spacer(modifier = Modifier.height(15.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
            }
        }
    }
}