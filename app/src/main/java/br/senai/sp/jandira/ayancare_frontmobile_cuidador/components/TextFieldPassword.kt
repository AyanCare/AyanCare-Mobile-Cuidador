package br.senai.sp.jandira.ayancare_frontmobile_cuidador.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPassword(label : String, valor: String, aoMudar: (String) -> Unit ){

    var passwordVisibilityState by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = valor,
        onValueChange = { aoMudar(it) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        visualTransformation =
        if(!passwordVisibilityState){
            PasswordVisualTransformation()
        }else{
            VisualTransformation.None
        },
        label = {
            Text(
                text = label
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisibilityState = !passwordVisibilityState
                }
            ) {
                Icon(
                    imageVector =
                    if(passwordVisibilityState){
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    },
                    contentDescription = null,
                    tint = Color(0xFFA7A5A4)

                )
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldPasswordPreview() {
    TextFieldPassword( label = "Senha", valor = "", aoMudar = {})
}