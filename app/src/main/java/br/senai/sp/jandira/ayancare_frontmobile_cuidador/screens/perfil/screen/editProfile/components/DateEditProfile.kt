package br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateEditProfile(
    context: Context,
    selectedDate: String,
    onDateChange: (String) -> Unit,
    focusManager: FocusManager,
    datePickerState: DatePickerState,
    validateDate: Boolean,
    validateDateError: String,
    label: String
) {
    var showDatePickerDialog by remember { mutableStateOf(false) }
    var editedDate by remember { mutableStateOf(selectedDate) }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePickerDialog = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formattedDate = millis.toBrazilianDateFormat()
                                editedDate = formattedDate
                                onDateChange(formattedDate)
                        }
                        showDatePickerDialog = false
                    }
                ) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }

    TextField(
        value = editedDate,
        onValueChange = {
            editedDate = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog = true
                }
            }
            .background(Color.White),
        label = {
            Text("$label")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        readOnly = true,
        trailingIcon = {
            IconButton(
                onClick = {
                    showDatePickerDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = ""
                )
            }
        }
    )

    if (!validateDate) {
        Text(
            text = validateDateError,
            color = Color.Red,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
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