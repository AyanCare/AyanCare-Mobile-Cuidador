package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.menuBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KingBed
import androidx.compose.material.icons.filled.LocalHotel
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val  title : String,
    val icon : ImageVector,
){
//    object  Cadendary : BottomBarScreen(
//        route = "cadendary",
//        title = "Calendário",
//        icon = Icons.Default.DateRange
//    )
    object  Stock : BottomBarScreen(
        route = "stock",
        title = "Paciente",
        icon = Icons.Default.LocalHotel
    )
    object  Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object  Profile : BottomBarScreen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )

}