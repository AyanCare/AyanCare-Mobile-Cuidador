package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.menuBar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconDefault(icon: ImageVector) {

    Column (
        modifier = Modifier
            .background(
                color = Color(53, 34, 95),
                shape = CircleShape
            )
            .size(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Icon(
            imageVector = icon,
            contentDescription = "Navigation Icon",
            modifier = Modifier
                .size(25.dp),
            tint = Color.White
        )
    }

}