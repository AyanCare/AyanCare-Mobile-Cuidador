package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Description
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
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.ConectarResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.service.Conectar
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.components.CardLinkedAccounts
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.components.FloatingActionButtonConectarContas
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LinkedAccountsScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    localStorage: Storage
) {
    val context = LocalContext.current

    val array = CuidadorRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()

    var listCuidadores by remember {
        mutableStateOf<List<Conectar>>(emptyList())
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getConectar().getConectar(id.toInt())

    call.enqueue(object : Callback<ConectarResponse> {
        override fun onResponse(
            call: Call<ConectarResponse>,
            response: Response<ConectarResponse>
        ) {
            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                listCuidadores = emptyList()
            } else {
                listCuidadores = response.body()!!.cuidadores
            }
        }
        override fun onFailure(call: Call<ConectarResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }

    })

    if (listCuidadores.isEmpty()) {
        Surface(
            color = Color(248, 240, 236)
        ) {
            Column(
                //verticalArrangement = Arrangement.SpaceBetween,
                //horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(top = 20.dp, start = 15.dp, end = 15.dp)
                    .fillMaxSize()
            ) {
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
                        text = "Contas Vinculadas",
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF090A0A),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        imageVector = Icons.Default.Description,
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Text(
                        text = "Não existe nenhum cuidador\nno momento",
                        fontSize = 16.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }
            }
            FloatingActionButtonConectarContas(navController, lifecycleScope)
        }
    }else{
        Surface(
            color = Color(248, 240, 236)
        ) {
            Column(
                //verticalArrangement = Arrangement.SpaceBetween,
                //horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(top = 20.dp, start = 15.dp, end = 15.dp)
                    .fillMaxSize()
            ) {
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
                        text = "Contas Vinculadas",
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF090A0A),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(){
                        items(listCuidadores){
                            CardLinkedAccounts(
                                onUnlinkClick = {  },
                                onProfileClick = {
                                    navController.navigate("profile_caregiver_screen")
                                    localStorage.salvarValor( context, it.id.toString(), "id_cuidador" )
                                                 },
                                nome = it.nome,
                                id = it.id
                                //foto =
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
            FloatingActionButtonConectarContas(navController, lifecycleScope)
        }
    }


}