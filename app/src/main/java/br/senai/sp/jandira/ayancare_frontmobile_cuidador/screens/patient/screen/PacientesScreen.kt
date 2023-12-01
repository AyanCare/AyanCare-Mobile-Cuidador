package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.components.ResearchField
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage

@Composable
fun PacienteScreen(
    navController: NavController,
    localStorage: Storage,
    navRotasController: NavController
) {

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Meus Pacientes",
                fontSize = 36.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF35225F)
            )
            Spacer(modifier = Modifier.height(15.dp))
                ResearchField(localStorage = localStorage, navController = navController,
                    navRotasController = navRotasController
                )
        }
    }
}



@Preview
@Composable
fun PacienteScreenPreview() {


    //PacienteScreen()
}