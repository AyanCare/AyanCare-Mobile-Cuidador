package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.Screen.EventScreen.Screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cor.CorResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.cor.service.Cor
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.DateEvent
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.DropdownDiasSemana
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.HeaderEvent
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.OptionEvent
import br.senai.sp.jandira.ayancare_frontmobile.screens.event.components.TimeTextField
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.CustomTextFieldValidate
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.NotificationService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.EventRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.Screen.EventScreen.components.DropdownPaciente
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    localStorage: Storage
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var selecionado by remember { mutableStateOf("evento") }
    var nameState by remember { mutableStateOf("") }
    var localSelecionado by remember { mutableStateOf("") }
    var descricaoSelecionada by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var selectedColorId by remember { mutableStateOf<Int?>(1) }
    var selectedColorHex by remember { mutableStateOf<String?>(null) }
    val datePickerState = rememberDatePickerState()
    var switchState by remember { mutableStateOf(false) }

    var selectedDrop by remember { mutableStateOf("") }

    var selectedItems by remember {
        mutableStateOf(listOf<String>())
    }


    //VALIDAÇÕES
    var validateName by rememberSaveable {
        mutableStateOf(true)
    }
    var validateLocal by rememberSaveable {
        mutableStateOf(true)
    }
    var validateDescricao by rememberSaveable {
        mutableStateOf(true)
    }
    var validateDate by rememberSaveable {
        mutableStateOf(true)
    }

    val validateNameError = "Nome do Evento está em branco"
    val validateLocalError = "Local está em branco"
    val validateDescricaoError = "Descrição está em branco"
    val validateDateError = "Data está em branco"

    //Log.e("TAG", "validateDate: $validateDate")
    fun validateData(
        name: String,
        local: String,
        descricao: String,
        date: String
    ): Boolean {
        //Log.d("TAG", "Data recebida: $date")

        validateName = name.isNotBlank()
        validateLocal = local.isNotBlank()
        validateDescricao = descricao.isNotBlank()
        validateDate = date.matches(Regex("\\d{2}/\\d{2}/\\d{4}"))

        //Log.d("TAG", "validateDate: $validateDate")

        return validateName && validateLocal && validateDescricao && validateDate
    }

//    val array = CuidadorRepository(context = context).findUsers()
//    val paciente = array[0]
//    var id = paciente.id.toLong()

    var id = localStorage.lerValor(context, "id_paciente")
    Log.i("id_paciente", "Calendary: $id")

    var listCor by remember {
        mutableStateOf(
            listOf(
                Cor(0, "")
            )
        )
    }

    //Cria uma chamada para o endpoint
    var call = RetrofitFactory.getCor().getCor()

    call.enqueue(object : Callback<CorResponse> {
        override fun onResponse(
            call: Call<CorResponse>,
            response: Response<CorResponse>
        ) {
            //Log.e("TAG", "onResponse: ${response.body()}")
            if (response.body()!!.status == 404) {
                Log.e("TAG", "a resposta está nula")
                listCor = emptyList()
            } else {
                listCor = response.body()!!.cores
            }
            //Log.e("TAG", "onResponse: $listCor")
        }

        override fun onFailure(call: Call<CorResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }
    })


    fun event(
        nome: String,
        descricao: String,
        local: String,
        hora: String,
        dia: String,
        idPaciente: Int,
        idCor: Int
    ) {
        if (validateData(nome, local, descricao, dia)) {
            val eventRepository = EventRepository()
            lifecycleScope.launch {
                val response = eventRepository.registerEvent(
                    nome,
                    descricao,
                    local,
                    hora,
                    dia,
                    idPaciente,
                    idCor
                )
                if (response.isSuccessful) {
                    Log.e(MainActivity::class.java.simpleName, "event bem-sucedido")
                    Log.e("event", "event: ${response.body()}")
                    val checagem = response.body()?.get("status")

                    Log.e("event", "event: ${checagem}")

                    if (checagem.toString() == "404") {
                        Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                    } else {

                        NotificationService().showNotification(
                            context = context,
                            title = "Evendo criado",
                            description = "Seu Evento foi criado com sucesso com o paciente",
                            Icon = R.drawable.calendario
                        )



                        Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()

                        navController.navigate("main_screen")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()

                    Log.e(MainActivity::class.java.simpleName, "Erro durante o event: $errorBody")
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            //Log.e("TAG", "EventScreen: $validateName = $validateLocal = $validateDescricao = $validateDate = $selectedDate", )
            //Log.e("TAG", "EventScreen: ${validateData(nome, local, descricao, dia)}", )
            Toast.makeText(context, "Por favor, reolhe suas caixas de texto", Toast.LENGTH_SHORT).show()
        }
    }

    fun eventSemanal(
        nome: String,
        descricao: String,
        local: String,
        hora: String,
        dias: List<String>,
        idPacienteCuidador: Int,
        idCor: Int
    ) {

            val eventRepository = EventRepository()
            lifecycleScope.launch {
                val response = eventRepository.registerEventSemanal(
                    nome,
                    descricao,
                    local,
                    hora,
                    id_paciente_cuidador = 1,
                    dias = dias,
                    cor_id = idCor
                )
                if (response.isSuccessful) {
                    Log.e(MainActivity::class.java.simpleName, "event bem-sucedido")
                    Log.e("event", "event: ${response.body()}")
                    val checagem = response.body()?.get("status")

                    Log.e("event", "event: ${checagem}")

                    if (checagem.toString() == "404") {
                        Toast.makeText(context, "algo está invalido", Toast.LENGTH_LONG).show()
                    } else {

                        NotificationService().showNotification(
                            context = context,
                            title = "Evendo criado",
                            description = "Seu Evento foi criado com sucesso com o paciente",
                            Icon = R.drawable.calendario
                        )



                        Toast.makeText(context, "Sucesso!!", Toast.LENGTH_SHORT).show()

                        navController.navigate("main_screen")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()

                    Log.e(MainActivity::class.java.simpleName, "Erro durante o event: $errorBody")
                    Toast.makeText(context, "algo está invalido", Toast.LENGTH_SHORT).show()
                }
            }
    }

    Surface(
        color = Color(248, 240, 236)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row {
                Button(
                    onClick = {
                        navController.navigate("main_screen")
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFE7EAEE))
                ) {
                    Text(
                        text = "Cancelar",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF64748B),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 12.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
//                        selectedColorId = 0
//                        if (selectedColorId!! == 0){
//                            Toast.makeText(context, "escolha sua cor", Toast.LENGTH_SHORT).show()
//                        }else{
//                            Toast.makeText(context, "sua cor foi escolhida", Toast.LENGTH_SHORT).show()
//                        }

                        Log.e("uysgfjh", "EventScreen: $selectedColorId")

                        if (!switchState) {
                            event(
                                nome = nameState,
                                descricaoSelecionada,
                                localSelecionado,
                                selectedTime,
                                selectedDate,
                                idPaciente = id!!.toInt(),
                                idCor = selectedColorId!!
                            )
                        }else{
                            eventSemanal(
                                nome = nameState,
                                descricaoSelecionada,
                                localSelecionado,
                                selectedTime,
                                selectedItems,
                                idPacienteCuidador = 1,
                                idCor = selectedColorId!!
                            )
                        }

                        Log.e("TAG", "EventScreen: $selectedDate")
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF35225F))
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                        tint = Color(0xFFFFFFFF)
                    )
                    //Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Criar Evento",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 6.dp, bottom = 6.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
        Column(
            //verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 80.dp)
                .fillMaxSize()
        ) {
            HeaderEvent(navController)
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = "Título",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF191D23)
                )
                CustomTextFieldValidate(
                    value = nameState,
                    onValueChange = { nameState = it },
                    label = "Nome do Evento",
                    showError = !validateName,
                    errorMessage = validateNameError,
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
//                Text(
//                    text = "Paciente",
//                    fontSize = 15.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins)),
//                    fontWeight = FontWeight(600),
//                    color = Color(0xFF191D23)
//                )
//                DropdownPaciente(
//                    context,
//                    gender = selectedDrop,
//                    onValueChange = {
//                        selectedDrop = it
//                    }
//                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cor",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(400),
                            color = if (selectedColorId == 0) Color.Red else Color(0xFF64748B)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        listCor.forEach { cor ->
                            val components = cor.hex.split(", ").map {
                                if (it.isNotEmpty()) {
                                    it.toInt()
                                } else {
                                    0
                                }
                            }
                            if (components.size == 3) {
                                val color = Color(
                                    red = components[0] / 255f,
                                    green = components[1] / 255f,
                                    blue = components[2] / 255f,
                                    alpha = 1f
                                )
                                Card(
                                    Modifier
                                        .size(16.dp)
                                        .background(color, CircleShape)
                                        .clickable {
                                            selectedColorId = cor.id
                                            selectedColorHex = cor.hex
                                        },
                                    shape = CircleShape,
                                    backgroundColor = color
                                ) {}

                                Spacer(modifier = Modifier.width(8.dp))
                            }

                        }
                    }
                    if (selectedColorHex?.isNotEmpty() == true) {
                        Log.e("TAG", "EventScreen: $selectedColorId")
                        Log.e("TAG", "EventScreen: $selectedColorHex")
                        val components = selectedColorHex!!.split(", ").map {
                            if (it.isNotEmpty()) {
                                it.toInt()
                            } else {
                                0
                            }
                        }
                        Log.e("TAG", "EventScreen: $components")
                        if (components.size == 3) {
                            val color = Color(
                                red = components[0] / 255f,
                                green = components[1] / 255f,
                                blue = components[2] / 255f,
                                alpha = 1f
                            )
                            Box(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(20.dp)
                                    .background(
                                        color = color,
                                        shape = CircleShape
                                    )
                            ) {}
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable {
                                selecionado = "evento"
                            }
                    ) {
                        Text(
                            text = "Evento",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = if (selecionado == "evento") Color.Black else Color(0xFF64748B)
                        )
                        Box(
                            modifier = Modifier
                                .width(155.dp)
                                .height(2.dp)
                                .background(if (selecionado == "evento") Color(0xFF047857) else Color.Transparent),
                        ) {}
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable {
                                selecionado = "dia"
                            }
                    ) {
                        Text(
                            text = "Dia e Horário",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(400),
                            color = if (selecionado == "dia") Color.Black else Color(0xFF64748B)
                        )
                        Box(
                            modifier = Modifier
                                .width(155.dp)
                                .height(2.dp)
                                .background(if (selecionado == "dia") Color(0xFF047857) else Color.Transparent)
                        ) {}
                    }
                }
                if (selecionado == "evento") {
                    Column() {
                        Spacer(modifier = Modifier.height(40.dp))
                        OptionEvent(
                            localState = localSelecionado,
                            descricaoState = descricaoSelecionada,
                            validateLocal = validateLocal,
                            validateLocalError = validateLocalError,
                            validateDescricao = validateDescricao,
                            validateDescricaoError = validateDescricaoError,
                            focusManager = focusManager,
                            onValueChange = {
                                localSelecionado = it
                            },
                            aoMudar = {
                                descricaoSelecionada = it
                            }
                        )
                    }
                } else if (selecionado == "dia") {
                    Column {
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            text = "Data",
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF191D23)
                        )
                        Switch(
                            checked = switchState,
                            onCheckedChange = { isChecked ->
                                switchState = isChecked
                            }
                        )
                        DropdownDiasSemana(
                            isEnabled = switchState,
                            onClick = {

                            },
                            diasSelecionados = selectedItems, // Passando os dias selecionados como parâmetro
                            onDiasSelecionadosChange = { selectedItems = it } // Recebendo a alteração dos dias selecionados
                        )
                        if (!switchState) {
                            DateEvent(
                                context = context,
                                selectedDate = selectedDate,
                                onDateChange = {
                                    selectedDate = it
                                },
                                focusManager = focusManager,
                                datePickerState = datePickerState,
                                validateDate = validateDate,
                                validateDateError = validateDateError
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        TimeTextField { formattedTime ->
                            selectedTime = formattedTime
                        }
                    }
                }
            }
        }
    }
}
