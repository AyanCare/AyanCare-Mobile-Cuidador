package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.telasInstrucoes.telaInstrucao3.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.Header

@Composable
fun TelaInstrucao3Screen(navController: NavController) {

    Column (
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Header()
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Trazendo conectividade",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                color = Color(0xFF35225F),
                textAlign = TextAlign.Center
            )
            Text(
                text = "para o usuario",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                color = Color(0xFF35225F),
                textAlign = TextAlign.Center
            )
        }
        Image(
            painter = painterResource(id = R.drawable.instrucao3),
            contentDescription = "",
            modifier = Modifier
                .padding(6.dp)
                .width(300.dp)
                .height(300.dp)
        )
        Text(
            text = "mais conforto de dentro da sua casa",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(500),
            color = Color(0xFF35225F),
            textAlign = TextAlign.Center
        )
        Row (){
            Card(
                Modifier
                    .width(10.dp)
                    .height(10.dp),
                backgroundColor = Color(0xFFBEA5F5)
            ) {}
            Spacer(modifier = Modifier.width(8.dp))
            Card(
                Modifier
                    .width(10.dp)
                    .height(10.dp),
                backgroundColor = Color(0xFFBEA5F5)
            ) {}
            Spacer(modifier = Modifier.width(8.dp))
            Card(
                Modifier
                    .width(10.dp)
                    .height(10.dp),
                backgroundColor = Color(0xFF35225F)
            ) {}

        }
        DefaultButton(
            text = "Ir para o app",
            onClick = {
                navController.navigate("main_screen")
            }
        )
        Text(
            text = "Pular",
            fontSize = 14.sp,
            color = Color.Transparent,
        )
    }

}