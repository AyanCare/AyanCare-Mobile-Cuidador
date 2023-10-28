package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.menuBar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.menuBar.components.IconDefault

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navRotasController: NavController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(
            navController = navController,
            navRotasController = navRotasController
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    val screen = listOf(
        BottomBarScreen.Stock,
        BottomBarScreen.Cadendary,
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentdestination = navBackStackEntry?.destination

    //Log.e("View", "MenuScreenA: ${viewModel.id}")

    BottomNavigation(
        modifier =  Modifier
            .height(80.dp),
        elevation = 0.dp,
    ){
        screen.forEach{screen ->
            AddItem(screen = screen, currentDestination = currentdestination, navController = navController)
            //Log.e("View", "MenuScreenB: ${viewModel.id}")
        }
    }
    //Log.e("View", "MenuScreenC ${viewModel.id}")

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    val currentRoute = currentDestination?.route
    val selected = currentRoute == screen.route

    BottomNavigationItem(
        modifier = Modifier
            .background(Color(248, 240, 236 )),
        label = {
            Text(
                text = screen.title,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF35225F)
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                modifier = Modifier
                    .size(30.dp),
                tint =
                if(selected){
                    IconDefault(
                        icon = screen.icon
                    )
                    Color(255, 255, 255, 0)
                }else {
                    Color(53, 34, 95)
                }
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route)
        },
        //selectedContentColor = Color(53, 34, 95)
    )

}
