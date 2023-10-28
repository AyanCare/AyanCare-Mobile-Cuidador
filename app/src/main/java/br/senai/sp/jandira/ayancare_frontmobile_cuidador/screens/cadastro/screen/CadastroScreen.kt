package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.cadastro.screen

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material3.Checkbox
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
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.cadastro.components.ProgressBar
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.MainActivity
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.CustomOutlinedTextField
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.DefaultButton
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.components.Wave
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.repository.CadastroRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.retrofit.user.service.UserService
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.Storage
import kotlinx.coroutines.launch
import org.json.JSONObject

@Composable
fun CadastroScreen(
    navController: NavController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    localStorage: Storage
) {

    lateinit var apiService: UserService

    var checked by remember {
        mutableStateOf(false)
    }

    var nameState by remember {
        mutableStateOf("")
    }

    var emailState by remember {
        mutableStateOf("")
    }

    var passwordState by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var repeatPasswordState by remember {
        mutableStateOf("")
    }

    var validateName by rememberSaveable {
        mutableStateOf(true)
    }

    var validateEmail by rememberSaveable {
        mutableStateOf(true)
    }

    var validatePassword by rememberSaveable {
        mutableStateOf(true)
    }

    var validateConfirmPassword by rememberSaveable {
        mutableStateOf(true)
    }

    var validateArePasswordEqual by rememberSaveable {
        mutableStateOf(true)
    }

    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var isConfirmPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    val validateNameError = "Nome não pode ficar em branco"
    val validateEmailError = "O formato do e-mail não é válido"
    val validatePasswordError =
        "Deve misturar letras maiúsculas e minúsculas, pelo menos um número, caracter especial e mínimo de 8 caracteres"
    val validateEqualPasswordError = "As senhas devem ser iguais"

    fun validateData(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}\$".toRegex()

        validateName = name.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePassword = passwordRegex.matches(password)
        validateConfirmPassword = passwordRegex.matches(confirmPassword)
        validateArePasswordEqual = password == confirmPassword

        return validateName && validateEmail && validatePassword && validateConfirmPassword && validateArePasswordEqual
    }

    fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if(validateData(name, email, password, confirmPassword)){
            val userRepository = CadastroRepository()
            lifecycleScope.launch {

                val response = userRepository.registerUser(name, email, password)

                if (response.isSuccessful) {

                    val jsonString = response.body().toString()
                    val jsonObject = JSONObject(jsonString)
                    val cuidadorObject = jsonObject.getJSONObject("cuidador")
                    val id = cuidadorObject.getInt("id")
                    val nome = cuidadorObject.getString("nome")
                    val email = cuidadorObject.getString("email")
                    val senha = cuidadorObject.getString("senha")
                    val token = jsonObject.getString("token")

                    Log.d(MainActivity::class.java.simpleName, "Registro bem-sucedido")

                    localStorage.salvarValor(context, nameState, "nome")
                    localStorage.salvarValor(context, id.toString(), "id_paciente")
                    localStorage.salvarValor(context, token.toString(), "token_paciente")
                    localStorage.salvarValor(context, nome.toString(), "nome_paciente")
                    localStorage.salvarValor(context, email.toString(), "email_paciente")
                    localStorage.salvarValor(context, senha.toString(), "senha_paciente")

                    Log.i("TAG", "register: $id + $token + $nome + $email + $senha ")

                    navController.navigate("finalizar_cadastro_screen")

                } else {

                    val errorBody = response.errorBody()?.string()
                    Log.e(MainActivity::class.java.simpleName, "Erro durante o registro: $errorBody")
                    Toast.makeText(context, "Erro durante o registro", Toast.LENGTH_SHORT).show()

                }
            }
        } else {
            Toast.makeText(context, "Por favor, reolhe suas caixas de texto", Toast.LENGTH_SHORT).show()
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
                    text = "Criar Conta",
                    fontSize = 40.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
                Text(
                    text = "Insira seus dados para se registrar",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF9E8BC1),
                    textAlign = TextAlign.Center,
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                CustomOutlinedTextField(
                    value = nameState,
                    onValueChange = { nameState = it },
                    label = "Digite um nome de usuário",
                    showError = !validateName,
                    errorMessage = validateNameError,
                    leadingIconImageVector = Icons.Default.PermIdentity,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    borderColor = Color.Black
                )

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
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    borderColor = Color.Black
                )

                CustomOutlinedTextField(
                    value = repeatPasswordState,
                    onValueChange = { repeatPasswordState = it },
                    label = "Repita a senha",
                    showError = !validateConfirmPassword || !validateArePasswordEqual,
                    errorMessage = if (!validateConfirmPassword) validatePasswordError else validateEqualPasswordError,
                    isPasswordField = true,
                    isPasswordVisible = isConfirmPasswordVisible,
                    onVisibilityChange = { isConfirmPasswordVisible = it },
                    leadingIconImageVector = Icons.Default.Password,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    borderColor = Color.Black
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        }
                    )
                    Text(
                        text = "Aceito Todos ",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = "Termos de Uso",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                        textDecoration = TextDecoration.Underline
                    )
                }

            }
            Column(
                modifier = Modifier.width(190.dp)
            ) {
                DefaultButton(
                    text = "Proximo",
                    onClick = {
                        register(
                            name = nameState,
                            email = emailState,
                            password = passwordState,
                            confirmPassword = repeatPasswordState
                        )
                    }
                )
            }
            ProgressBar(text = "1 / 4", valor = 83)

        }
    }

}
