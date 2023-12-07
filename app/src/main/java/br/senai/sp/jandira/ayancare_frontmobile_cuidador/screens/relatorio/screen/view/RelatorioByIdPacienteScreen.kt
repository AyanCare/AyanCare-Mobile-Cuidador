package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.view

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
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage

@Composable
fun RelatorioByIdPacienteScreen(
    navController: NavController,
    localStorage: Storage
) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var descricao_relatorio = localStorage.lerValor(context, "descricao_relatorio")
    var id_paciente_relatorio = localStorage.lerValor(context, "id_paciente_relatorio")
    var nome_paciente_relatorio = localStorage.lerValor(context, "nome_paciente_relatorio")
    var idade_paciente_relatorio = localStorage.lerValor(context, "idade_paciente_relatorio")
    var genero_paciente_relatorio = localStorage.lerValor(context, "genero_paciente_relatorio")

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
                    navController.navigate("relatorios_paciente_screen")
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
                Text(
                    text = "$nome_paciente_relatorio",
                    fontSize = 25.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F)
                )
                Text(
                    text = "#$id_paciente_relatorio",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9986BD)
                )
                Text(
                    text = "$idade_paciente_relatorio anos, $genero_paciente_relatorio",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9986BD)
                )

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
                        value = "$descricao_relatorio",
                        onValueChange = {},
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
                        ),
                        enabled = false
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DefaultButton(
                        onClick = {
                            navController.navigate("view_question_screen")
                        },
                        text = "Questionário"
                    )
                }
            }
        }
    }
}