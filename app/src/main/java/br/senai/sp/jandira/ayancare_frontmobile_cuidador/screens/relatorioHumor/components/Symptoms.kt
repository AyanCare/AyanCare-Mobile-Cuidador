package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorioHumor.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Symptoms(
    text: String,
    onClick: () -> Unit
) {

    var selected = true

    FilterChip(
        onClick = { onClick() },
        label = {
            Text(
                text = text,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = if (selected)Color.White else Color(0xFF35225F)
            )
        },
        shape = RoundedCornerShape(15.dp),
        selected = selected,
        colors = FilterChipDefaults.filterChipColors(
            labelColor = Color(0xFF35225F),
            selectedLabelColor = Color(0xFFFFFFFF),
            selectedLeadingIconColor = Color(0xFFFFFFFF),
            selectedContainerColor = Color(0xFF35225F)
        )
    )
}