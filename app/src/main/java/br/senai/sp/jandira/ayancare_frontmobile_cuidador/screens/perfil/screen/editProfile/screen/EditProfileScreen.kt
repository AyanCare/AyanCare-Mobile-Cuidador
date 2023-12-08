package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.perfil.screen.editProfile.screen

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.CuidadorResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.PacienteResponse
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Cuidador
import br.senai.sp.jandira.ayancare_frontmobile.retrofit.patient.service.Paciente
import br.senai.sp.jandira.ayancare_frontmobile.screens.perfil.screen.editProfile.components.DateEditProfile
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultTextField
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.RetrofitFactory
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.CadastroRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.MaskVisualTransformation
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.finalizarCadastro.components.DropdownGender
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.finalizarCadastro.screen.UploadingImageToFireBase
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.perfil.components.BoxProfile
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.funcaoQueChamaSqlLite.deleteUserSQLite
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.funcaoQueChamaSqlLite.saveLogin
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.security.auth.callback.Callback


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(

    navController: NavController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope
){
    val context = LocalContext.current
    var isDialogVisibleChronicDiseases by remember { mutableStateOf(false) }

    var isDialogVisibleComorbidity by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val focusManager = LocalFocusManager.current

    var selectedDate by remember { mutableStateOf("") }
    var selectedDrop by remember { mutableStateOf("") }

    var validateDate by rememberSaveable {
        mutableStateOf(true)
    }

    val validateDateError = "Data está em branco"

    val scrollState = rememberScrollState()


    val  array = CuidadorRepository(context = context).findUsers()


    val cuidador = array[0]
    var id = cuidador.id.toLong()
    var email = cuidador.email
    var token = cuidador.token
    var genero = cuidador.genero
    var descricaoExperiencia = cuidador.descricaoExperiencia
    selectedDrop = genero

    //FireBase
    val isUploading = remember { mutableStateOf(false) }
    val img: Bitmap =
        BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.alert_dark_frame)
    val bitmap = remember {
        mutableStateOf(img)
    }

    var imagemState by remember {
        mutableStateOf<Uri?>(null)
    }


    val launcherImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)

        } else {
            val source = it?.let { it1 ->
                ImageDecoder.createSource(context.contentResolver, it1)
            }
            bitmap.value = source?.let { it1 -> ImageDecoder.decodeBitmap(it1) }!!
        }

        imagemState = it

    }

    var nomeState by remember {
        mutableStateOf("")
    }
    var cpfState by remember {
        mutableStateOf("")
    }
    var experienciaState by remember {
        mutableStateOf("")
    }
    var senha by remember {
        mutableStateOf("")
    }

    var listCuidador by remember {
        mutableStateOf(
            Cuidador(
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                0,
                "",
                "",
                0,
                0,
                "",
                ""
            )
        )
    }

    var  call = RetrofitFactory.getCuidador().getCuidadorById(id.toString())


    call.enqueue(object : retrofit2.Callback<CuidadorResponse> {
        override fun onResponse(
            call: Call<CuidadorResponse>,
            response: Response<CuidadorResponse>
        ) {
            listCuidador = response.body()!!.cuidador


            selectedDate = listCuidador.data_nascimento

            nomeState = listCuidador.nome
            selectedDate = listCuidador.data_nascimento
            experienciaState = listCuidador.descricao_experiencia

//            Log.i("chegueiii", "onResponse: ${listCuidador.foto}")

        }
        override fun onFailure(call: Call<CuidadorResponse>, t: Throwable) {
            Log.i("ds3t", "onFailure: ${t.message}")
        }


    })


    fun atualizarDados(
        token: String,
        id: Int,
        nome: String,
        data_nascimento: String,
        email: String,
        senha:String,
        foto: String,
        experiencia:String,
        id_endereco_cuidador: Int,
        genero: String
    ) {
        val userRepository = CadastroRepository()
        lifecycleScope.launch {

            val response = userRepository.updateUser(
                token,
                id,
                nome,
                data_nascimento,
                email,
                senha,
                foto,
                experiencia,
                id_endereco_cuidador,
                genero

            )


            Log.e("response", "finalizarCadastro: $response")

            if (response.isSuccessful) {
                deleteUserSQLite(context, id)

                saveLogin(
                    context = context,
                    token = token!!,
                    id = id.toLong(),
                    nome = nome,
                    dataNascimento = selectedDate,
                    email = email,
                    foto = foto,
                    experiencia = experiencia,
                    genero = selectedDrop,
                    tipo = "Cuidador"
                )

                Log.d(MainActivity::class.java.simpleName, "Registro bem-sucedido")

                navRotasController.navigate("main_screen")

            } else {

                val errorBody = response.errorBody()?.string()
                Log.e(MainActivity::class.java.simpleName, "Erro durante o registro: $errorBody")
                Toast.makeText(context, "Erro durante o registro", Toast.LENGTH_SHORT).show()

            }
        }
    }


    Surface(
        color = Color(248, 240, 236)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            BoxProfile()
            IconButton(
                onClick = {
                    navRotasController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Column(
                //verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 110.dp, start = 15.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center),
                            shape = CircleShape,
                            border = BorderStroke(
                                width = 2.dp,
                                Brush.horizontalGradient(
                                    listOf(
                                        colorResource(id = R.color.purple_200),
                                        Color.White
                                    )
                                )
                            )
                        ) {
                            AsyncImage(
                                model = if (imagemState != null) {
                                    imagemState
                                } else {
                                    listCuidador.foto
                                },
                                contentDescription = "",
                                modifier = Modifier
                                    .size(100.dp)
                                    .border(
                                        BorderStroke(4.dp, Color.White),
                                        CircleShape
                                    )
                                    .padding(4.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Image(
                            painter = painterResource(
                                id = R.drawable.baseline_camera_alt_24
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .align(alignment = Alignment.BottomEnd)
                                .offset(x = 3.dp, y = 3.dp)
                                .size(30.dp)
                                .clickable {

                                    launcherImage.launch("image/*")
                                },
                        )
                    }
                    OutlinedTextField(
                        value = nomeState,
                        onValueChange = {
                            //nomeState = it
                            if (it.length <= 50) {
                                nomeState = it
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp),
                        label = {
                            Text(
                                text = "Nome Completo"
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Log.i("data chegando", "EditProfileScreen: $selectedDate")
                DateEditProfile(
                    context = context,
                    selectedDate = selectedDate,
                    onDateChange = {
                        selectedDate = it
                    },
                    focusManager = focusManager,
                    datePickerState = datePickerState,
                    validateDate = validateDate,
                    validateDateError = validateDateError,
                    label = "$selectedDate"
                )

                Spacer(modifier = Modifier.height(16.dp))

                DropdownGender(
                    context = context,
                    gender = selectedDrop,
                    onValueChange = { selectedDrop = it }
                )


                Spacer(modifier = Modifier.height(30.dp))

                OutlinedTextField(
                    value = experienciaState,
                    onValueChange = {
                        //nomeState = it
                        if (it.length <= 50) {
                            experienciaState = it
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    label = {
                        Text(
                            text = "Coloque a sua experiencia"
                        )
                    }
                )


                Spacer(modifier = Modifier.height(30.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    DefaultButton(
                        onClick = {

                            Log.e("TAG", "EditProfileScreen: $token", )
                            Log.e("TAG", "EditProfileScreen: $id", )
                            Log.e("TAG", "EditProfileScreen: $nomeState", )
                            Log.e("TAG", "EditProfileScreen: $experienciaState", )
                            Log.e("TAG", "EditProfileScreen: $selectedDate", )
                            Log.i("joaoooo", "EditProfileScreen:  $imagemState")

                            if (id != null) {
                                isUploading.value = true
                                bitmap.value?.let { bitmap ->
                                    UploadingImageToFireBase(
                                        bitmap,
                                        context as ComponentActivity
                                    ) { imageURL ->
                                        if (imageURL != null) {
                                            // Aqui, imageURL contém a URL da imagem após o upload bem-sucedido
//                                        Toast.makeText(
//                                            context,
//                                            "Upload Bem-Sucedido. URL: $imageURL",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
                                            Toast.makeText(
                                                context,
                                                "Upload Bem-Sucedido.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            // Continue com a função finalizarCadastro, passando a imageURL se necessário
                                            atualizarDados(
                                                token = token.toString(),
                                                id = id.toInt(),
                                                nome = nomeState.toString(),
                                                data_nascimento = selectedDate.toAmericanDateFormat1(),
                                                email = email.toString(),
                                                senha = senha.toString(),
                                                foto = imageURL, // Use a imageURL
                                                id_endereco_cuidador = 1,
                                                experiencia = experienciaState.toString(),
                                                genero = selectedDrop
                                            )
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Falha ao fazer o upload",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            }
                        },
                        text = "Salvar"
                    )
                }

            }




        }

    }
}



fun String.toAmericanDateFormat1(
    pattern: String = "yyyy-MM-dd"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}