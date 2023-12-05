package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.AlarmeRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.LoginRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TomeiouNao(
    isOpen: Boolean,
//    navController: NavController,
//    localStorage: Storage,
    lifecycleScope: LifecycleCoroutineScope,
    id: Int
) {

    val context = LocalContext.current

    fun updateAlarmeUnitario(
        id_status_alarme: Int
    ){
        val alarmeRepository = AlarmeRepository()
        lifecycleScope.launch {

            val response = alarmeRepository.updateAlarmeUnitario(id_status_alarme)

            if (response.isSuccessful) {
                Log.e(MainActivity::class.java.simpleName, "alarmeUnitario bem-sucedido")
                Log.e("alarmeUnitario", "alarmeUnitario: ${response.body()}")
                val checagem = response.body()?.get("status")

                if (checagem.toString() == "404") {
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()
                    //navController.navigate("add_stock_screen")
                }
            } else {
                val errorBody = response.errorBody()?.string()

                Log.e(MainActivity::class.java.simpleName, "Erro durante o alarmeUnitario: $errorBody")
                Toast.makeText(context, "algo está invalido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Dialog(
            onDismissRequest = {
                isOpen
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
                        text = "Já tomou o remédio?",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF54595E),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    updateAlarmeUnitario(1)
                                }
                        ){
                            Text(
                                text = "Tomei",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center
                            )
                            Icon(
                                imageVector =  Icons.Default.CheckCircle,
                                contentDescription = "",
                                tint = Color(76, 175, 80, 255),
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable{
                                    updateAlarmeUnitario(0)
                                }
                        ){
                            Text(
                                text = "Não Tomei",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center
                            )
                            Icon(
                                imageVector =  Icons.Default.Cancel,
                                contentDescription = "",
                                tint = Color(236, 38, 38, 255),
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}