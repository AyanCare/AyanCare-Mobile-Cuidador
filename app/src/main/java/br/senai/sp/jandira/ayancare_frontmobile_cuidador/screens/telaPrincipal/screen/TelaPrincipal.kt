package br.senai.sp.jandira.ayancare_frontmobile.screens.telaPrincipal.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.R
import br.senai.sp.jandira.ayancare_frontmobile.components.DefaultButton


@Composable
fun TelaPrincipalScreen(navController: NavController) {

    Surface (
        color = Color(0xFF35225F)
    ){
        Box (
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(top = 100.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.onda_tela_principal
                    ),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.BottomEnd
                )

            }
            Image(
                painter = painterResource(id = R.drawable.imagem_principal),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                contentScale = ContentScale.Crop
            )


        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ){
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 50.dp)
            ){
                Text(
                    text = "Bem-Vindo",
                    fontSize = 40.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "ao nosso app de cuidados",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center,

                    )
                Spacer(modifier = Modifier.height(54.dp))

                DefaultButton(text = "Entrar", onClick = { navController.navigate("login_screen")})

                Spacer(modifier = Modifier.height(20.dp))

                DefaultButton(text = "Cadastrar", onClick = {navController.navigate("cadastro_screen")})
            }

        }

    }
}




//@Preview(showBackground = true)
//@Composable
//fun TelaPrincipalPreview() {
//    TelaPrincipalScreen()
//}