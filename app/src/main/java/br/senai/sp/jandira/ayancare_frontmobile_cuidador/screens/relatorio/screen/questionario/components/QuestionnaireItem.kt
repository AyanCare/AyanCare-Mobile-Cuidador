package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.questionario.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.questionario.service.Questionario

@Composable
fun QuestionnaireList(listQuestionario: List<String>) {
    val respostaList = remember { mutableStateListOf<Boolean>() }

    LazyColumn(
        modifier = Modifier.height(500.dp)
    ) {
        itemsIndexed(listQuestionario) { index, pergunta ->
            var respostaSelecionada by remember { mutableStateOf(false) }

            Column {
                Text(
                    text = pergunta,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9986BD)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = respostaSelecionada,
                        onClick = {
                            respostaSelecionada = true
                            respostaList[index] = true
                        }
                    )
                    Text(
                        text = "Sim",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF35225F)
                    )
                    RadioButton(
                        selected = !respostaSelecionada,
                        onClick = {
                            respostaSelecionada = false
                            respostaList[index] = false
                        }
                    )
                    Text(
                        text = "Não",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF35225F)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

