package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.perfil.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoxProfile() {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(
                brush = Brush
                    .verticalGradient(
                        listOf(
                            Color(53, 34, 95),
                            Color(28, 18, 50),
                            Color(0, 0, 0)
                        )
                    )
            )
    ) {}
}
