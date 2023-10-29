package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.screen.profileCaregiver.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CircleProfile(
    painter: String
) {
    AsyncImage(
        model = "$painter",
        contentDescription = "",
        modifier = Modifier
            .size(100.dp)
            .border(
                BorderStroke(4.dp, Color.White),
                CircleShape
            )
            .padding(4.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}