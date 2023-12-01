package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable
fun ButtonCalendary(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick,
        modifier = Modifier
            .width(150.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF35225F))
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(500),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center
        )
    }

}