package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.pacientes.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.pacientes.components.CardPaciente

@Composable
fun PacienteScreen(
    navController: NavController
) {

    var isDialogVisibleConect by remember { mutableStateOf(false) }

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Meus Pacientes",
                fontSize = 36.sp,
                //fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFF35225F)
            )
            Spacer(modifier = Modifier.height(25.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            LazyColumn(){
                items(10){
                    CardPaciente(
                        onUnlinkClick = {
                            isDialogVisibleConect = true
                        },
                        onProfileClick = {},
                        nome = "Janice Nelson",
                        id = 1597,
                        foto = "it.foto_cuidador",
                        ano = 84
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }
    }
}

@Preview
@Composable
fun PacienteScreenPreview() {


    //PacienteScreen()
}