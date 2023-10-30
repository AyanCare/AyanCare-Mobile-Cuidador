package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FloatingActionButtonRelatorio(
    navController: NavController
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier
                .animateContentSize()
                .padding(10.dp)
        ) {
            FloatingActionButton(
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .heightIn(60.dp, 120.dp),
                //backgroundColor = Color.Blue,
                //contentColor = Color.White
            ) {
                Text(
                    text = if (expanded) "Fechar" else "Adicionar Relátorio",
                    modifier = Modifier.padding(12.dp)
                )
            }

            AnimatedVisibility(
                visible = expanded,
                modifier = Modifier.padding(start = 8.dp),
            ) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("add_relatorio_screen")
                    },
                    modifier = Modifier.size(56.dp),
                    //backgroundColor = Color.Red,
                    //contentColor = Color.White
                ) {
                    Icon(Icons.Filled.Add, "Adicionar Relátorio")
                }
            }
        }
    }
}
