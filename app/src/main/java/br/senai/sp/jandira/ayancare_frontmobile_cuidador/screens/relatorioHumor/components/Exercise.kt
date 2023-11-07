package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorioHumor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import coil.compose.AsyncImage

@Composable
fun Exercise(
    text: String,
    foto: String,
    onClick: () -> Unit
) {

    var selected = true

    Box(
        modifier = Modifier
            .background(
                if (selected) Color(0xFF35225F) else Color.Transparent,
                shape = RoundedCornerShape(14.dp)
            )
            .border(1.dp, Color(0xFF35225F), shape = RoundedCornerShape(14.dp))
            .clickable {
                onClick()
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
        ) {
            AsyncImage(
                model = "$foto" ,
                contentDescription = "",
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = text,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = if (selected) Color.White else Color(0xFF9986BD)
            )
        }
    }

}