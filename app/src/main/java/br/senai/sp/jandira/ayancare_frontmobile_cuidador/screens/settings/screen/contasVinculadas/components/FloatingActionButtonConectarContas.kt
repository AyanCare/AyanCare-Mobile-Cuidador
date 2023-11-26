package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.components

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController

@Composable
fun FloatingActionButtonConectarContas(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
) {
    var expanded by remember { mutableStateOf(false) }

    var isDialogVisibleConect by remember { mutableStateOf(false) }

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
                    text = if (expanded) "Fechar" else "Adicionar Conexão",
                    modifier = Modifier.padding(12.dp)
                )
            }

            AnimatedVisibility(
                visible = expanded,
                modifier = Modifier.padding(start = 8.dp),
            ) {
                FloatingActionButton(
                    onClick = {
                        isDialogVisibleConect = true
                    },
                    modifier = Modifier.size(56.dp),
                    //backgroundColor = Color.Red,
                    //contentColor = Color.White
                ) {
                    Icon(Icons.Filled.Add, "Adicionar Conexão")
                }
            }
            if (isDialogVisibleConect) {
                ModalAddConect(
                    isDialogVisibleConect = false,
                    navController = navController,
                    nav = "linked_accounts_screen"
                )
            }
        }
    }
}

