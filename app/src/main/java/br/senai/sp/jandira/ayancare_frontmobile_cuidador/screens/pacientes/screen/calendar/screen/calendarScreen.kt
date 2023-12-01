package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.pacientes.screen.calendar.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage

@Composable
fun CalendarScreen(
    navController: NavController,
    localStorage: Storage
){

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {

        }

    }

}