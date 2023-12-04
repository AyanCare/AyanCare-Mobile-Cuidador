package br.senai.sp.jandira.ayancare_frontmobile.screens.event.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable
fun DropdownDiasSemana(isEnabled: Boolean) {
    var diasSelecionados by remember { mutableStateOf(setOf<String>()) }
    var dropdownAberto by remember { mutableStateOf(false) }

    val diasDaSemana = listOf(
        "Domingo",
        "Segunda-feira",
        "Terça-feira",
        "Quarta-feira",
        "Quinta-feira",
        "Sexta-feira",
        "Sábado"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var inputText by remember { mutableStateOf("Selecione os dias da semana") }

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = inputText,
                onValueChange = {
                    inputText = it
                    dropdownAberto = true
                },
                readOnly = true,
                textStyle = TextStyle(color = if (isEnabled) Color.Black else Color.Gray),
                enabled = isEnabled, // Habilita ou desabilita o TextField
                trailingIcon = {
                    IconButton(
                        onClick = {
                            dropdownAberto = !dropdownAberto
                        },
                        enabled = isEnabled, // Habilita ou desabilita o IconButton
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = if (isEnabled) Color.Black else Color.Gray
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = dropdownAberto,
                onDismissRequest = { dropdownAberto = false }
            ) {
                diasDaSemana.forEach { dia ->
                    val isChecked = diasSelecionados.contains(dia)

                    androidx.compose.material.DropdownMenuItem(onClick = {
                        if (isChecked) {
                            diasSelecionados -= dia
                        } else {
                            diasSelecionados += dia
                        }
                    }) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = dia,
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF191D23)
                            )
                            if (isChecked) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Dias selecionados: ${diasSelecionados.joinToString(", ")}",
            color = if (isEnabled) Color.Black else Color.Gray,
            fontSize = 15.sp
        )
    }
}
