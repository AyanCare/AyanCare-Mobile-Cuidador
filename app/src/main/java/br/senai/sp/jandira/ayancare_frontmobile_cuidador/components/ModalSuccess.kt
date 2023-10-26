package br.senai.sp.jandira.ayancare_frontmobile_cuidador.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ModalSuccess(
    onDismiss: () -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }

    val coroutineScope = rememberCoroutineScope()

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                // Não faça nada aqui, forçaremos o fechamento após 5 segundos
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(0.5.dp)
                    .width(300.dp)
                    .height(226.dp)
                    .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 40.dp, top = 36.dp, end = 40.dp, bottom = 36.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ModalWithVideo()

            }
        }

        // Use uma coroutine para fechar o modal após 5 segundos
        coroutineScope.launch {
            delay(4000) // 5 segundos
            showDialog = false
            onDismiss()
        }
    }
}


@Composable
fun ModalWithVideo() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_lnw3ncco))
    var isPlaying by remember { mutableStateOf(true) }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
    )

    LaunchedEffect(key1 = progress){
        if (progress == 0f){
            isPlaying= true
        }
        if (progress == 1f){
            isPlaying= false
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .size(100.dp),
            progress = progress
        )
        Text(
            text = "Successfully accepted!",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(600),
            color = Color(0xFF54595E),
            textAlign = TextAlign.Center
        )

    }
}