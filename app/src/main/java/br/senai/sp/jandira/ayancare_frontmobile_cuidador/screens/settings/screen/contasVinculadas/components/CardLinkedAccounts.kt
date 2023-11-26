package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.R
import coil.compose.AsyncImage

@Composable
fun CardLinkedAccounts(
    onUnlinkClick: () -> Unit,
    onProfileClick: () -> Unit,
    nome: String,
    id: Int,
    foto: String
) {
    var isExpanded by remember { mutableStateOf(false)}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        //elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = "$foto",
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = nome,
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF35225F)
                        )
                        Text(
                            text = "#$id",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF9986BD)
                        )
                    }

                    IconButton(
                        onClick = { isExpanded = !isExpanded }
                    ) {
                        if (isExpanded) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "",
                                modifier = Modifier.size(30.dp)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.ArrowDropUp,
                                contentDescription = "",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.run { height(16.dp) })
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onUnlinkClick,
                        modifier = Modifier
                            .width(150.dp)
                            .height(35.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF9A9A9A))
                    ) {
                        Text(
                            text = "Desvincular",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF)
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        onClick = onProfileClick,
                        modifier = Modifier
                            .width(150.dp)
                            .height(35.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF35225F))
                    ) {
                        Text(
                            text = "Ver Perfil",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
            }
        }
    }
}
