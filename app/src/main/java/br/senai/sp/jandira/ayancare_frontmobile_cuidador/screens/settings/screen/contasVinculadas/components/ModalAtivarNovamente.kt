package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.conectar.service.AtivarContaResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ModalAtivarNovamente(
    isDialogVisibleConect: Boolean,
    navController: NavController,
    localStorage: Storage,
    id_paciente: Int,
    nome_paciente: String
) {

    val context = LocalContext.current
    val array = CuidadorRepository(context = context).findUsers()

    val cuidador = array[0]
    var id = cuidador.id.toLong()
    //var nome_paciente = paciente.nome

    //val id_cuidador = localStorage.lerValor(context, "id_cuidador_conexao")
    //val nome_cuidador = localStorage.lerValor(context, "nome_cuidador_conexao")


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Dialog(
            onDismissRequest = {
                isDialogVisibleConect
            }
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shape = RoundedCornerShape(8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 28.dp, bottom = 28.dp, start = 15.dp, end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Deseja ativar novamente sua conexão com o cuidador $nome_paciente?",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF54595E),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Button(
                            onClick = {

                                Log.e("ativarConta", "ModalAtivarNovamenteConect: $id + $id_paciente")

                                var call = RetrofitFactory.getConectar().updateConectAtivar(id.toInt(), id_paciente!!.toInt())

                                call.enqueue(object : Callback<AtivarContaResponse> {
                                    override fun onResponse(
                                        call: Call<AtivarContaResponse>,
                                        response: Response<AtivarContaResponse>
                                    ) {
                                        Log.e("ativarConta", "onResponse: ${response.body()}")
                                    }
                                    override fun onFailure(call: Call<AtivarContaResponse>, t: Throwable) {
                                        Log.i("ativarConta", "onFailure: ${t.message}")
                                    }
                                })
                                navController.navigate("linked_accounts_screen")
                            },
                            modifier = Modifier
                                .width(90.dp)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF9A9A9A))
                        ) {
                            Text(
                                text = "Sim",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFEFE),
                                textAlign = TextAlign.Center
                            )
                        }
                        Button(
                            onClick = {
                                navController.navigate("linked_accounts_screen")
                            },
                            modifier = Modifier
                                .width(90.dp)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF35225F))
                        ) {
                            Text(
                                text = "Não",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFEFE),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}
