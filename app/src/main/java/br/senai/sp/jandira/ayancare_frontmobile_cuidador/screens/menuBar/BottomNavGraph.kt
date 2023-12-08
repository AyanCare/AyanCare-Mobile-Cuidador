package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.menuBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.home.screen.HomeScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.PacienteScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.perfil.screen.ProfileScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    navRotasController: NavController,
    localStorage: Storage
) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ){

        composable(route = BottomBarScreen.Stock.route){
            PacienteScreen(navController = navController, localStorage = localStorage, navRotasController)
        }
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController = navController, navRotasController =  navRotasController)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(navController = navController, navRotasController = navRotasController)
        }
    }
}
