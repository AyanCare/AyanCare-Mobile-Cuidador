package br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.contasVinculadas.screen.profileCaregiver.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ScrollState
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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.CuidadorResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PacienteResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.ComorbidadesList
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.DoencasCronicasList
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Paciente
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.contasVinculadas.screen.profileCaregiver.components.CardTask
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.profile.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.profile.components.CircleProfile
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileCaregiverScreen(
    navController: NavController,
    //navRotasController: NavController,
    localStorage: Storage
){
    val context = LocalContext.current

    val scrollState = rememberScrollState()


    var id = localStorage.lerValor(context, "id_paciente_conexao")
    val token = localStorage.lerValor(context, "token_paciente")


    Log.e("TAG","ProfileCaregiverScreen: $id")


    var listPacientes by remember{

        mutableStateOf(
            Paciente(
                id = 0,
                nome = "",
                data_nascimento = "",
                email = "",
                senha = "",
                cpf = "",
                foto = "",
                historico_medico = "",
                doencas_cronicas = emptyList(),
                comorbidades = emptyList()
            )
        )
    }


    //Criar uma Chamada para endPoint
    var call = RetrofitFactory.getPatient().getPatientById(token = token.toString(), id = id)

    call.enqueue(object : retrofit2.Callback<PacienteResponse> {
        override fun onResponse(
            call: Call<PacienteResponse>,
            response: Response<PacienteResponse>
        ){
            listPacientes = response.body()!!.paciente
        }

        override fun onFailure(call: Call<PacienteResponse>, t: Throwable){
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
        ){
            BoxProfile()
            androidx.compose.material.IconButton(
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
                text = listPacientes.nome,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000)
            )

            Text(
                text = "Paciente",
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
                    text = listPacientes.data_nascimento,
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