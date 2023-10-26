package br.senai.sp.jandira.ayancare_frontmobile_cuidador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.ui.theme.AyanCareCuidadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AyanCareCuidadorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val navController = rememberNavController()
                    var context = LocalContext.current

                    //TelaPrincipalScreen(navController = navController)
                }
            }
        }
    }
}