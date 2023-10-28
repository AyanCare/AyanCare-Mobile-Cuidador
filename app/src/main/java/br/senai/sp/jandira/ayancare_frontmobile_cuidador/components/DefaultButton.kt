package br.senai.sp.jandira.ayancare_frontmobile_cuidador.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable

fun DefaultButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick,
        modifier = Modifier
            .width(280.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF35225F))
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(600),
            color = Color.White
        )
    }

}
@Preview(showBackground = true)
@Composable
fun DefaultButtonPreview() {
    DefaultButton(
        text = "Entrar",
        onClick = {}
    )
}