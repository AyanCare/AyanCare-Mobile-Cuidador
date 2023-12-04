package br.senai.sp.jandira.ayancare_frontmobile.screens.event.components

import android.app.TimePickerDialog
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun TimeTextField(
    onTimeSelected: (String) -> Unit // Adicione um parâmetro de retorno de String
) {
    var selectedTime by remember { mutableStateOf(Calendar.getInstance()) }
    val formatter = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = "${formatter.format(selectedTime.time)}",
            onValueChange = {
                //showTimePicker = true
            },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            readOnly = true,
            trailingIcon = {
                IconButton(
                    onClick = {
                        showTimePicker = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.LockClock,
                        contentDescription = ""
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BackHandler(enabled = showTimePicker) {
            showTimePicker = false
        }

        if (showTimePicker) {
            val timePickerDialog = TimePickerDialog(
                LocalView.current.context,
                { _, hourOfDay, minute ->
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)

                    showTimePicker = false
                    val formattedTime = formatter.format(selectedTime.time)
                    onTimeSelected(formattedTime) // Passa o valor formatado para onTimeSelected
                },
                selectedTime.get(Calendar.HOUR_OF_DAY),
                selectedTime.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }
    }
}