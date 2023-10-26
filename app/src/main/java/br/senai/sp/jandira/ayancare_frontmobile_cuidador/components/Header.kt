package br.senai.sp.jandira.ayancare_frontmobile_cuidador.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable
fun Header() {
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier
                .width(60.dp)
                .height(43.dp),
            contentDescription ="logo"
        )
        Column {
            Image(
                painter = painterResource(id = R.drawable.nome_marca),
                modifier = Modifier
                    .width(96.dp)
                    .height(25.dp)
                ,
                contentDescription ="nome da marca"
            )
            Text(
                text = "cuidados está em suas mãos",
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFFE2784A),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 6.dp)
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header()
}