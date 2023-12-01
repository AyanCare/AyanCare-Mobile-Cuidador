package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable
fun TextEditCalendary(text:String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.poppins)),
        fontWeight = FontWeight(400),
        color = Color(0xFFAFAFAF),
        textAlign = TextAlign.Center
    )
}