package br.senai.sp.jandira.ayancare_frontmobile.screens.event.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable
fun OptionDate(
    selectDate: String,
    onValueChange: (String) -> Unit
) {

    val context = LocalContext.current

    var isExpanded by remember {
        mutableStateOf(false)
    }

   var selectedDate = selectDate

    Column {
//        Switch(
//            checked = isExpanded,
//            onCheckedChange = { isExpanded = it },
//            colors = SwitchDefaults.colors(
//                checkedThumbColor = Color(0xFF35225F), // Cor do Switch quando está ligado
//                checkedTrackColor = Color(0xFF35225F).copy(alpha = 0.5f), // Cor da faixa quando está ligado
//                uncheckedThumbColor = Color.Gray, // Cor do Switch quando está desligado
//                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f) // Cor da faixa quando está desligado
//            )
//        )
//        Text(
//            text = "Dias da semana",
//            fontSize = 15.sp,
//            fontFamily = FontFamily(Font(R.font.poppins)),
//            fontWeight = FontWeight(600),
//            color = if (isExpanded) Color.Black else Color.Gray
//        )
//        DropdownDiasSemana(isEnabled = isExpanded)
//        Spacer(modifier = Modifier.height(15.dp))
//        if(isExpanded){
//            Text(
//                text = "Horário",
//                fontSize = 15.sp,
//                fontFamily = FontFamily(Font(R.font.poppins)),
//                fontWeight = FontWeight(600),
//                color = Color(0xFF191D23)
//            )
//            TimeTextField()
//        }else{
            Text(
                text = "Data",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color(0xFF191D23)
            )
//            DateEvent(
//                context = context,
//                selectedDate = selectedDate,
//                onDateChange = { onValueChange(it) }
//            )
            Spacer(modifier = Modifier.height(12.dp))
            //TimeTextField()
        //}

    }
}