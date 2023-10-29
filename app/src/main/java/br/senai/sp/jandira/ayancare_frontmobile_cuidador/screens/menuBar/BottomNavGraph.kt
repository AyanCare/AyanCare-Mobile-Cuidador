package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.menuBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.home.screen.HomeScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    navRotasController: NavController
) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ){
        composable(route = BottomBarScreen.Cadendary.route){
            //CalendaryScreen()
        }
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController = navController, navRotasController =  navRotasController)
        }
        composable(route = BottomBarScreen.Stock.route){
            //EstoqueScreen(navRotasController = navRotasController, navController = navController)
        }
        composable(route = BottomBarScreen.Profile.route){
            //ProfileScreen(navController = navController, navRotasController)
        }
    }
}
