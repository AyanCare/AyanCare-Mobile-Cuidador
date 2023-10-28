package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
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
fun Line() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                . fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box (
                modifier = Modifier
                    .height(1.dp)
                    .width(100.dp)
                    .background(Color(128, 128, 128)),
            ){}
            Text(
                text = "ou Continue com",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(700),
                color = Color(0xFFA7A5A4)
                )
            Box (
                modifier = Modifier
                    .height(1.dp)
                    .width(100.dp)
                    .background(Color(128, 128, 128)),
            ){}
        }
    }
}
