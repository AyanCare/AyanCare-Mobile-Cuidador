package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.finalizarCadastro.screen

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.finalizarCadastro.components.DropdownGender
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DateTextField
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.TextFieldNumber
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.CadastroRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.cadastro.components.ProgressBar
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.funcaoQueChamaSqlLite.saveLogin
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun FinalizarCadastroScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    localStorage: Storage
) {

    val context = LocalContext.current

    var experienciaState by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedDrop by remember { mutableStateOf("") }

    //FireBase
    val isUploading = remember { mutableStateOf(false) }
    val img: Bitmap =
        BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_gallery)
    val bitmap = remember {
        mutableStateOf(img)
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
    }

    val id = localStorage.lerValor(context, "id_paciente")
    val token = localStorage.lerValor(context, "token_paciente")
    val nome = localStorage.lerValor(context, "nome_paciente")
    val email = localStorage.lerValor(context, "email_paciente")
    val senha = localStorage.lerValor(context, "senha_paciente")

    val id_paciente = id?.toInt()

    fun finalizarCadastro(
        token: String,
        id: Int,
        nome: String,
        data_nascimento: String,
        email: String,
        senha: String,
        foto:String,
        descricaoExperiencia: String,
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
                descricaoExperiencia,
                id_endereco_cuidador,
                genero
            )

            Log.e("response", "finalizarCadastro: $response")

            if (response.isSuccessful) {

                saveLogin(
                    context = context,
                    id = id.toLong(),
                    token = token!!,
                    nome = nome,
                    email = email,
                    dataNascimento = selectedDate,
                    foto = foto,
                    experiencia = descricaoExperiencia,
                    genero = selectedDrop,
                    tipo = "Cuidador"
                )


                Log.d(MainActivity::class.java.simpleName, "Registro bem-sucedido")

                navController.navigate("add_experience_screen")

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
        Wave()
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Finalizar Cadastro",
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
                Text(
                    text = "Preencha os dados restantes para finalizar seu cadastro.",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9E8BC1),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp),
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
                            Image(
                                bitmap = bitmap.value.asImageBitmap(),
                                contentDescription = "imagem do usuário",
                                //colorFilter = ColorFilter.tint(colorResource(id = R.color.pink_login)),
                                modifier = Modifier.fillMaxSize(),
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
                    Spacer(modifier = Modifier.height(40.dp))

                    DateTextField(
                        context,
                        selectedDate,
                        onDateChange = { selectedDate = it }
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    DropdownGender(
                        context = context,
                        gender = selectedDrop,
                        onValueChange = { selectedDrop = it }
                    )

                    // Agora você pode acessar 'dateTyped' que contém o valor digitado
                    //Text("genero digitada na tela principal: ${cpfState + selectedDate + selectedDrop}")
                }

            }
            Column(
                modifier = Modifier.width(190.dp)
            ) {
                DefaultButton(
                    text = "Finalizar",
                    onClick = {
                        if (id_paciente != null) {
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
                                        finalizarCadastro(
                                            token = token.toString(),
                                            id = id_paciente.toInt(),
                                            nome = nome.toString(),
                                            data_nascimento = selectedDate.toAmericanDateFormat(),
                                            email = email.toString(),
                                            senha = senha.toString(),
                                            foto = imageURL, // Use a imageURL
                                            descricaoExperiencia = experienciaState,
                                            id_endereco_cuidador = 1,
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
                    }
                )
            }
            ProgressBar(text = "2 / 3", valor = 220)
        }
    }
}

fun String.toAmericanDateFormat(
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


//Função de Upload
fun UploadingImageToFireBase(
    bitmap: Bitmap,
    context: ComponentActivity,
    callback: (String?) -> Unit
) {

    val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("images/${bitmap}")

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)

    val imageData = baos.toByteArray()

    imageRef.putBytes(imageData)
        .addOnSuccessListener { taskSnapshot ->
            // Upload bem-sucedido
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                // Aqui você obtém a URL da imagem após o upload
                val imageURL = uri.toString()
                callback(imageURL)
            }.addOnFailureListener { e ->
                // Tratar erro ao obter a URL
                callback(null)
                Log.e("FirebaseStorage", "Erro ao obter a URL da imagem: $e")
            }
        }
        .addOnFailureListener { e ->
            // Tratar erro ao fazer o upload
            callback(null)
            // Você pode imprimir uma mensagem de erro ou registrar o erro para depuração
            Log.e("FirebaseStorage", "Erro ao fazer upload da imagem: $e")
        }
}