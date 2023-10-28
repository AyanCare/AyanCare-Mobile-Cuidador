package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.cadastro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable
fun ProgressBar(text: String, valor: Int) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = text,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(400),
            color = Color(0xFFFFFFFF)
        )
        Row (
            modifier = Modifier
                .width(330.dp)
                .height(5.dp)
                .background(
                    color = Color(0x809986BD),
                    shape = RoundedCornerShape(size = 5.dp)
                )
        ){
            Row (
                modifier = Modifier
                    .width(valor.dp)
                    .height(5.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 5.dp))
            ){}
        }

    }

}

@Preview
@Composable
fun BarraProgressoPreview() {
    ProgressBar(text = "1 / 3", valor = 110)
}