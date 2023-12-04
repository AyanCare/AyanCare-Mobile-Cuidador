package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen

import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service.Alarmes
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.calendario.service.EventosUnicos
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.components.Calendary
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.components.OptionAlarmCalendary
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.components.OptionEventCalendary

@Composable
fun CalendaryScreen(
    navController: NavController,
    navRotasController: NavController,
    localStorage: Storage,
) {
    var selecionado by remember { mutableStateOf("evento") }
    val scrollState = rememberScrollState()
    var context = LocalContext.current

    //var listAlarme: List<Alarmes> = listOf()

    var listAlarme by remember {
        mutableStateOf(listOf<Alarmes>())
    }
    var listEventoUnico by remember {
        mutableStateOf(listOf<EventosUnicos>())
    }

    Surface (
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Calendary(
                localStorage = localStorage,
                onChaneList = {
                    Log.e("IT", "$it")
                    listAlarme = it
                },
                onChaneListEvent = {
                    Log.e("IT", "$it")
                    listEventoUnico = it
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Button(
                    onClick = { selecionado = "evento" },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (selecionado == "evento") Color(0xFF35225F) else Color.White
                    )
                ) {
                    Text(
                        text = "Eventos",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = if (selecionado == "evento") Color.White else Color(0xFF35225F),
                        textAlign = TextAlign.Center
                    )
                }
                Button(
                    onClick = { selecionado = "alarme" },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (selecionado == "alarme") Color(0xFF35225F) else Color.White
                    )
                ) {
                    Text(
                        text = "Alarmes",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500),
                        color = if (selecionado == "alarme") Color.White else Color(0xFF35225F),
                        textAlign = TextAlign.Center
                    )
                }
            }
            if (selecionado == "evento") {
                Column (){
                    Spacer(modifier = Modifier.height(15.dp))
                    OptionEventCalendary(localStorage, listEventoUnico)
                }
            } else if (selecionado == "alarme") {
                Column {
                    Spacer(modifier = Modifier.height(15.dp))
                    OptionAlarmCalendary(localStorage, listAlarme)
                }
            }
        }
        if (selecionado == "evento"){
            Column(
                modifier = Modifier
                    .padding(end = 15.dp, bottom = 20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                SmallFloatingActionButton(
                    onClick = {
                        navRotasController.navigate("event_screen")
                    },
                    shape = CircleShape,
                    containerColor = Color(0xFF9986BD),
                    modifier = Modifier.size(65.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Adicionar Evento",
                        tint = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }
        }else{
            Column(
                modifier = Modifier
                    .padding(end = 15.dp, bottom = 20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                SmallFloatingActionButton(
                    onClick = {
                        navRotasController.navigate("add_remedy_screen")
                    },
                    shape = CircleShape,
                    containerColor = Color(0xFF9986BD),
                    modifier = Modifier.size(65.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Adicionar Alarme",
                        tint = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }
        }
    }
}