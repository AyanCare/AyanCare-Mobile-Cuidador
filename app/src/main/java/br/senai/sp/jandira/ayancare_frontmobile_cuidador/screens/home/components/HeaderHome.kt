package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HeaderHome(
    navController: NavController,
    navRotasController: NavController
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ){
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "leadingIconDescription",
            tint = Color(0xFF35225F),
            modifier = Modifier
                .size(35.dp)
        )
        IconButton(
            onClick = {
                navRotasController.navigate("setting_screen")
            }
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "leadingIconDescription",
                tint = Color(0xFF35225F),
                modifier = Modifier
                    .size(35.dp)
            )
        }
    }
}
