package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.sugestao

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.Wave

@Composable
fun SuggestionScreen(
    navController: NavController,
    navRotasController: NavController
) {

    var nomeState by remember {
        mutableStateOf("")
    }

    var descricaoState by remember {
        mutableStateOf("")
    }


    Surface(
        color = Color(248, 240, 236)
    ) {
        Wave()
        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 80.dp)
                .fillMaxSize()
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = ""
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                //verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = "Sugestões",
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(50.dp))

                Column {
                    Text(
                        text = "Nome",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        color = Color(0xFF191D23)
                    )
                    DefaultTextField(
                        valor = nomeState,
                        label = "",
                        onValueChange = { nomeState = it }
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                Column {
                    Text(
                        text = "Descrição",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        color = Color(0xFF191D23)
                    )
                    TextField(
                        value = descricaoState,
                        onValueChange = { descricaoState = it },
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

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DefaultButton(
                        onClick = { /*TODO*/ },
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
                                navController.popBackStack()
                            }
                    )
                }
            }
        }
    }
}
