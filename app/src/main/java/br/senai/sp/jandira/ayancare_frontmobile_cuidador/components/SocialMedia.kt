package br.senai.sp.jandira.ayancare_frontmobile_cuidador.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R

@Composable
fun SocialMedia(
    onClick: () -> Unit
) {

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .size(80.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .background(Color.White)
    ){
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "image description",
            modifier = Modifier.size(45.dp)
        )
    }


}





@Preview(showBackground = true)
@Composable
fun SocialMediaPreview() {
    SocialMedia(onClick = {})
}