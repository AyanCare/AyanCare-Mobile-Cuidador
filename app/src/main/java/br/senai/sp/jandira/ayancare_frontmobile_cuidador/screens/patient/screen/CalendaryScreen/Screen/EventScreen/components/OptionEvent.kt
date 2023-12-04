package br.senai.sp.jandira.ayancare_frontmobile.screens.event.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.CustomTextAreaValidate
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.CustomTextFieldValidate

@Composable
fun OptionEvent(
    localState: String,
    descricaoState: String,
    validateLocal: Boolean,
    validateLocalError: String,
    validateDescricao: Boolean,
    validateDescricaoError: String,
    focusManager: FocusManager,
    onValueChange: (String) -> Unit,
    aoMudar: (String) -> Unit,
) {

    var descricaoState = descricaoState
    var localState = localState

    Column {
        Text(
            text = "Nome do Local",
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(600),
            color = Color(0xFF191D23)
        )
//        DefaultTextField(
//            valor = localState,
//            label = "",
//            onValueChange = { onValueChange(it) },
//            aoMudar = { aoMudar(it) }
//        )
        CustomTextFieldValidate(
            value = localState,
            onValueChange = { onValueChange(it)},
            label = "",
            showError = !validateLocal,
            errorMessage = validateLocalError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            unfocusedBorderColor = Color(0xFF64748B),
            focusedBorderColor = Color(0xFF6650A4),
            textColor = Color(0xFF64748B)
        )
        Spacer(modifier = Modifier.height(5.dp))
//        Text(
//            text = "Paciente",
//            fontSize = 15.sp,
//            fontFamily = FontFamily(Font(R.font.poppins)),
//            fontWeight = FontWeight(600),
//            color = Color(0xFF191D23)
//        )
        //DropdownGender()
        //Spacer(modifier = Modifier.height(40.dp))
        Column {
            Text(
                text = "Descrição",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                color = Color(0xFF191D23)
            )
//            TextField(
//                value = descricaoState,
//                onValueChange = { onValueChange(it) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(140.dp)
//                    .border(
//                        width = 1.dp,
//                        color = Color(167, 165, 164),
//                        shape = RoundedCornerShape(4.dp)
//                    ),
//                colors = TextFieldDefaults.textFieldColors(
//                    backgroundColor = Color(248, 240, 236)
//                )
//            )
            CustomTextAreaValidate(
                value = descricaoState,
                onValueChange = { aoMudar(it)},
                label = "",
                showError = !validateDescricao,
                errorMessage = validateDescricaoError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.clearFocus() }
                ),
                unfocusedBorderColor = Color(0xFF64748B),
                focusedBorderColor = Color(0xFF6650A4),
                textColor = Color(0xFF64748B),
                height = 140
            )
        }
    }
}
