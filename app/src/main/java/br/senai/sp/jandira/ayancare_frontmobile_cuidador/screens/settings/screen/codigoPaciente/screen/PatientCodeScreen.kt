package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.codigoPaciente.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PatientCodeScreen(
    navController: NavController,
    navRotasController: NavController
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val clipboardManager = LocalClipboardManager.current
    //val clipboardManager = LocalContext.current.getSystemService(ClipboardManager::class.java)
    val keyboardController = LocalSoftwareKeyboardController.current

    // Crie uma variável para armazenar o status de cópia
    var copiadoComSucesso by remember { mutableStateOf(false) }


    val array = CuidadorRepository(context = context).findUsers()

    val paciente = array[0]
    var id = paciente.id.toLong()


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
                    text = "Código do Cuidador",
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF35225F),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "O código do cuidador para suas conexões com os pacientes",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9986BD),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(100.dp))

                TextField(
                    value = id.toString(),
                    onValueChange = {},
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFF9986BD),
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .padding(0.5.dp)
                        .width(200.dp)
                        .height(50.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 4.dp)
                        ),
                    label = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Código",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFFAFAFAF),
                            textAlign = TextAlign.Center
                        )

                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide() // Esconde o teclado ao pressionar "Done"
                        }
                    ),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    ),
                    enabled = false
                )

                Spacer(modifier = Modifier.height(100.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 60.dp, end = 60.dp)
                ) {
                    DefaultButton(
                        onClick = {
                            // Copiar o valor do TextField para a área de transferência
                            clipboardManager.setText(AnnotatedString("${id.toString()}"))
                            copiadoComSucesso = true
                        },
                        text = if(copiadoComSucesso){
                            "Copiado !!"
                        }else{
                            "Copiar"
                        }
                    )
                }
            }
        }
    }
}

