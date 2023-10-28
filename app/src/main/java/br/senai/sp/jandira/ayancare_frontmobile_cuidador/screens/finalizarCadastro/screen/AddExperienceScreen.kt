package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.finalizarCadastro.screen

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
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.cadastro.components.ProgressBar

@Composable
fun AddExperienceScreen(
    navController: NavController
) {

    val context = LocalContext.current
    var experienciaState by remember { mutableStateOf("") }

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
                        navController.navigate("tela_instrucao1_screen")
                    }
                )
            }
            ProgressBar(text = "3 / 3", valor = 330)
        }
    }
}