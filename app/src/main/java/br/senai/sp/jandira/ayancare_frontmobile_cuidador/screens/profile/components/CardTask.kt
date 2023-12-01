package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable
fun CardTask(
    nome: String,
    dia: String,
    hora: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        backgroundColor = Color(0xFF35225F),
        shape = RoundedCornerShape(size = 16.dp)
        //elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    imageVector = Icons.Default.Alarm,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "$nome.",
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                            modifier = Modifier.width(250.dp)
                        )
                        Text(
                            text = "Dia: $dia",
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(
                            text = "$hora",
                            fontSize = 13.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(400),
                            color = Color(0x99EBEBF5)
                        )
                    }
                }
            }
        }
    }
}