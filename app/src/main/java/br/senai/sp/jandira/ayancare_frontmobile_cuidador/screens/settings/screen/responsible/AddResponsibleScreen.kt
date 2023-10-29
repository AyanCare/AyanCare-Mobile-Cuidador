package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.responsible

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository

@Composable
fun AddResponsibleScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope
) {

    var isDialogVisibleSuccess by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val array = CuidadorRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()

    var nomeState by remember {
        mutableStateOf("")
    }

    var telefoneState by remember {
        mutableStateOf("")
    }

    var localState by remember {
        mutableStateOf("")
    }

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            //verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.PersonAddAlt1,
                contentDescription = "add responsible",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Adicionar Responsável",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            DefaultTextField(
                valor = nomeState,
                label = "Nome",
                onValueChange = { nomeState = it }
            )

            Spacer(modifier = Modifier.height(50.dp))

            DefaultTextField(
                valor = telefoneState,
                label = "Telefone",
                onValueChange = { telefoneState = it }
            )

            Spacer(modifier = Modifier.height(50.dp))

            Column {
                Text(
                    text = "Local:",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = Color(0xFF191D23)
                )
                TextField(
                    value = localState,
                    onValueChange = { localState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .border(
                            width = 1.dp,
                            color = Color(167, 165, 164),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(248, 240, 236)
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DefaultButton(
                    onClick = {},
                    text = "Adicionar"
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Cancelar",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("responsible_screen")
                        }
                )
            }
        }
    }
}
