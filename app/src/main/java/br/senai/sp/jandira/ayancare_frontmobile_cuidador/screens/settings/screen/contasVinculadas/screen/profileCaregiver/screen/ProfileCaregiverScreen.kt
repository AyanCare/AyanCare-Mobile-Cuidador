package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.screen.profileCaregiver.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
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
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PacienteResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Paciente
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.perfil.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.perfil.components.CircleProfile
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.screen.profileCaregiver.components.ProcessingProfile
import retrofit2.Call
import retrofit2.Response

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


    // Mantenha uma lista de  patients no estado da tela
    var listPaciente by remember {
        mutableStateOf(
            Paciente(
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                emptyList(),
                emptyList()
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
            listPaciente = response.body()!!.paciente
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
                painter = listPaciente.foto
            )

            Text(
                text = listPaciente.nome,
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

            Text(
                text = "Doenças Crônicas",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF35225F)
            )

            Spacer(modifier = Modifier.height(5.dp))

            LazyRow{
                items(listPaciente.doencas_cronicas.reversed()) {

                    var text = if (listPaciente.doencas_cronicas[0].nome == null){
                        "Não Existe Doenças Crônicas"
                    } else {
                        "${it.nome}"
                    }

                    ProcessingProfile(
                        text = text,
                        width = 200
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Comorbidade",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF35225F)
            )

            Spacer(modifier = Modifier.height(5.dp))

            LazyRow{
                items(listPaciente.comorbidades.reversed()) {

                    var text = if (listPaciente.comorbidades[0].nome == null){
                        "Não Existe Comorbidades"
                    } else {
                        "${it.nome}"
                    }

                    ProcessingProfile(
                        text = text,
                        width = 200
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Remédios",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF35225F)
            )

        }



    }

}