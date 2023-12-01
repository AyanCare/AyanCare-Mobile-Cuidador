package br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.contasVinculadas.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.conectar.ConectarResponse
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.TextFieldNumber
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.components.ModalAtivarNovamente
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ModalAddConect(
    isDialogVisibleConect: Boolean,
    navController: NavController,
    localStorage:Storage,
    nav: String
) {


    val context = LocalContext.current

    val array = CuidadorRepository(context = context).findUsers()

    val cuidaddor = array[0]
    var id = cuidaddor.id.toLong()

    var idState by remember {
        mutableStateOf("")
    }

    var nomeState by remember {
        mutableStateOf("")
    }

    //Vincular conta novamente
    var isDialogVisibleAtivarNovamente by remember { mutableStateOf(false) }

    val id_cuidador = localStorage.lerValor(context, "id_cuidador_conexao")
    val nome_cuidador = localStorage.lerValor(context, "nome_cuidador_conexao")




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
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Adicionar Conexão",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Caso esteja com duvida onde está o vá no APP do cuidador e procure por código do paciente",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF929292),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    TextFieldNumber(
                        valor = idState,
                        label = "Código do Paciente",
                        onValueChange = { idState = it }
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    DefaultButton(
                        onClick = {
                            //Cria uma chamada para o endpoint
                            var call = RetrofitFactory.getConectar().createConect(id.toInt(), idState.toInt())

                            Log.e("add_conect", "ModalAddConect: $id + $idState")

                            call.enqueue(object : Callback<ConectarResponse> {
                                override fun onResponse(
                                    call: Call<ConectarResponse>,
                                    response: Response<ConectarResponse>
                                ) {
                                    if (response.isSuccessful){
                                        Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                                        navController.navigate("linked_accounts_screen")
                                    }else{
                                        Log.e("add_conect", "onResponse:${response} ")
                                        if (response.code() == 409){
                                            val erro = response.errorBody()?.string()
                                            val erroObject = JSONObject(erro)
                                            val conexao = erroObject.getJSONObject("conexao")
                                            val status = conexao.getInt("status")

                                            nomeState = conexao.getString("paciente")

                                            Log.e("Luizão", "${conexao}")
                                            Log.e("Luizão", "${status}")
                                            if (status == 0){
                                                Toast.makeText(context, "Essa conexao está desativada!!", Toast.LENGTH_SHORT).show()
                                                isDialogVisibleAtivarNovamente = true
                                            }else{
                                                Toast.makeText(context, "Conexao já existente!!", Toast.LENGTH_SHORT).show()
                                            }
                                        }else{
                                            Toast.makeText(context, "Erro id inválido!!", Toast.LENGTH_SHORT).show()
                                        }
                                    }

                                }
                                override fun onFailure(call: Call<ConectarResponse>, t: Throwable) {
                                    Log.i("add_conect", "onFailure: ${t.message}")
                                }

                            })
                        },
                        text = "Salvar"
                    )
                    if (isDialogVisibleAtivarNovamente) {
                        ModalAtivarNovamente(
                            isDialogVisibleConect = false,
                            localStorage = localStorage,
                            navController = navController,
                            id_paciente = idState.toInt(),
                            nome_paciente = nomeState
                        )
                    }
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
                                navController.navigate("$nav")
                            }
                    )
                }
            }
        }
    }
}
