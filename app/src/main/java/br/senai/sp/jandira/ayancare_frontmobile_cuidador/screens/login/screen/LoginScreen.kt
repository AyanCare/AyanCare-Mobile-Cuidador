package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.login.screen

import android.Manifest
import android.os.Build
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.LoginRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.CustomOutlinedTextField
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.funcaoQueChamaSqlLite.deleteUserSQLite
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.funcaoQueChamaSqlLite.saveLogin
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import org.json.JSONObject


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var emailState by remember {
        mutableStateOf("")
    }

    var passwordState by remember {
        mutableStateOf("")
    }

    var validateEmail by rememberSaveable {
        mutableStateOf(true)
    }

    var validatePassword by rememberSaveable {
        mutableStateOf(true)
    }

    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    val validateEmailError = "O formato do e-mail não é válido"
    val validatePasswordError = "Deve misturar letras maiúsculas e minúsculas, pelo menos um número, caracter especial e mínimo de 8 caracteres"

    fun validateData(email: String, password: String): Boolean {
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}\$".toRegex()

        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePassword = passwordRegex.matches(password)

        return validateEmail && validatePassword
    }

    fun login (
        email: String,
        password: String
    ) {
        if(validateData(email, password)){
            val loginRepository = LoginRepository()
            lifecycleScope.launch {
                Log.e("TA", "login: $email e $password")

                val response = loginRepository.loginUser(email, password)

                Log.e("TAG", "login: ${response.body()}")

                if(response.isSuccessful){
                    Log.e(MainActivity::class.java.simpleName, "Login bem-sucedido" )
                    Log.e("login", "login: ${response.body()}")
                    val checagem = response.body()?.get("status")
                    if (checagem.toString() == "404") {
                        Toast.makeText(context, "Email ou senha inválido", Toast.LENGTH_LONG).show()
                    } else {

                        val token = response.body()?.get("token")?.asString

                        val jsonString = response.body().toString()
                        val jsonObject = JSONObject(jsonString)
                        val cuidadorObject = jsonObject.getJSONObject("cuidador")

                        val id = cuidadorObject.getInt("id")
                        val nome = cuidadorObject.getString("nome")
                        val email = cuidadorObject.getString("email")
                        val dataNascimento = cuidadorObject.getString("data_nascimento")
                        val foto = cuidadorObject.getString("foto")
                        val experiencia = cuidadorObject.getString("experiencia")
                        val genero = cuidadorObject.getString("genero")
                        val tipo_usuario = jsonObject.getString("tipo")

                        if (CuidadorRepository(context).findUsers().isEmpty()) {
                            saveLogin(
                                context = context,
                                id = id.toLong(),
                                token = token!!,
                                nome = nome,
                                email = email,
                                dataNascimento = dataNascimento,
                                foto = foto,
                                experiencia = experiencia,
                                genero = genero,
                                tipo = tipo_usuario
                            )

                             navController.navigate("main_screen")

                        } else {
                            //deleteUserSQLite(context = context , id)
                            saveLogin(
                                context = context,
                                id = id.toLong(),
                                token = token!!,
                                nome = nome,
                                email = email,
                                dataNascimento = dataNascimento,
                                foto = foto,
                                experiencia = experiencia,
                                genero = genero,
                                tipo = tipo_usuario
                            )

                            navController.navigate("main_screen")
                        }

                        Toast.makeText(context, "Seja bem-vindo", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    val errorBody = response.errorBody()?.string()

                    Log.e(MainActivity::class.java.simpleName, "Erro durante o login: $errorBody")
                    Toast.makeText(context, "Email ou senha inválido", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Por favor, reolhe suas caixas de texto", Toast.LENGTH_SHORT).show()
        }
    }


//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//        val permissionState: PermissionState = rememberPermissionState(
//            permission = Manifest.permission.POST_NOTIFICATIONS
//        )

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

                Text(
                    text = "Olá!",
                    fontSize = 35.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bem-vindo de volta!",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF35225F),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "sentimos sua falta.",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF35225F),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                ) {
                    CustomOutlinedTextField(
                        value = emailState,
                        onValueChange = { emailState = it },
                        label = "E-mail",
                        showError = !validateEmail,
                        errorMessage = validateEmailError,
                        leadingIconImageVector = Icons.Default.AlternateEmail,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        borderColor = Color.Black
                    )

                    CustomOutlinedTextField(
                        value = passwordState,
                        onValueChange = { passwordState = it },
                        label = "Senha",
                        showError = !validatePassword,
                        errorMessage = validatePasswordError,
                        isPasswordField = true,
                        isPasswordVisible = isPasswordVisible,
                        onVisibilityChange = { isPasswordVisible = it },
                        leadingIconImageVector = Icons.Default.Password,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.clearFocus() }
                        ),
                        borderColor = Color.Black
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Esqueceu a senha?",
                        modifier = Modifier
                            .clickable { navController.navigate("esquecer_senha_screen") },
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFA7A5A4)

                    )
                }

                Spacer(modifier = Modifier.height(40.dp))


                Column(
                    modifier = Modifier.width(190.dp)
                ) {
                    DefaultButton(
                        text = "Entrar",
                        onClick = {
                            //permissionState.launchPermissionRequest()
                            login(
                                emailState,
                                passwordState
                            )
                        })
                }

                // Line()

//            Row {
//                SocialMedia {}
//                Spacer(modifier = Modifier.width(70.dp))
//                SocialMedia {}
//            }

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Não tem conta?",
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFDFAF8),
                        textAlign = TextAlign.Right,
                    )
                    Text(
                        text = "Cadastre-se aqui.",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFDFAF8),
                        textAlign = TextAlign.Right,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .clickable { navController.navigate("cadastro_screen") }
                    )
                }

            }
//        }

//    }else{
//        val permissionState: PermissionState = rememberPermissionState(
//            permission = Manifest.permission.POST_NOTIFICATIONS
//        )
//
//        Text(text = "Aceite as notificções")
//
//        Button(onClick = {
//            permissionState.launchPermissionRequest()
//
//        }) {
//        }
//
//    }

        }
}
