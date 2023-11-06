package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.profile.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.profile.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.profile.components.CircleProfile
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.profile.components.CardTask
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository

@Composable
fun ProfileScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    val array = CuidadorRepository(context = context).findUsers()

    val cuidador = array[0]
    var id = cuidador.id.toLong()
    var foto = cuidador.foto
    var nome = cuidador.nome
    var descricao = cuidador.descricaoExperiencia

    Surface(
        color = Color(248, 240, 236)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            BoxProfile()
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Column(
                //verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 110.dp, start = 15.dp, end = 15.dp, bottom = 20.dp)
                    .fillMaxSize()
            ) {
                CircleProfile(
                    painter = foto
                )

                Text(
                    text = nome,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000)
                )

                Text(
                    text = "Cuidador",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = descricao,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF9986BD),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Tarefas de Hoje",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF35225F)
                )
                Spacer(modifier = Modifier.height(15.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
                Spacer(modifier = Modifier.height(10.dp))
                CardTask()
            }
        }
    }
}