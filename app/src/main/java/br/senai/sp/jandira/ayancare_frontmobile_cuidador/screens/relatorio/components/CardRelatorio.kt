package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable
fun CardRelatorio(
    text: String,
    data: String,
    horario: String,
    onClick: () -> Unit
) {

    val textSmall = if (text.length > 30) {
        text.substring(0, 30) + "..."
    } else {
        text
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.FileCopy,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp),
                tint = Color(0xFF9986BD)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = "Relatório",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.manrope)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F)
                )
                Text(
                    text = textSmall,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.manrope)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9986BD)
                )
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(end = 15.dp, top = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = data,
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.manrope)),
                fontWeight = FontWeight(800),
                color = Color(0xFF7E6F94)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = horario,
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.manrope)),
                fontWeight = FontWeight(700),
                color = Color(0xFF7E6F94)
            )
        }
    }
}

//@Preview
//@Composable
//fun fcdascvasd() {
//    CardRelatorio()
//}