package br.senai.sp.jandira.ayancare_frontmobile_cuidador.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(
    context: Context,
    selectedDate: String,
    onDateChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(showDatePickerDialog) {
        if (!showDatePickerDialog) {
            focusManager.clearFocus(force = true)
        }
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                androidx.compose.material3.Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                onDateChange(millis.toBrazilianDateFormat())
                            }
                        showDatePickerDialog = false
                    }) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }
    TextField(
        value = selectedDate,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog = true
                    focusManager.clearFocus(force = true)
                }

            }
            .background(Color.White),
        label = {
            Text("Data de Aniversário")
        },
        readOnly = true
    )
}

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy" //"yyyy-MM-dd"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}