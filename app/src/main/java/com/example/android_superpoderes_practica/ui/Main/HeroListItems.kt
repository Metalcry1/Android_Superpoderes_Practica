package com.example.android_superpoderes_practica.ui.Main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import com.example.android_superpoderes_practica.R

@Composable
fun HeroesListItem(hero: HeroUI, modifier: Modifier = Modifier, navController: NavController, ) {


    Card(
        modifier = modifier
            .width(200.dp)
            .fillMaxSize(),

        shape = RoundedCornerShape(40.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.Black
        )

    ) {
        var loading by remember {
            mutableStateOf(true)
        }

        var error by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .padding(8.dp)
                .size(200.dp, 300.dp)
                .background(Color.Red.copy(alpha = 0.5F))
        ) {


            Box(
                modifier = Modifier
                    .height(80.dp)
                    .padding(8.dp)
            ) {
                Text(
                    text = hero.name,
                    color = Color.Black,
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                        letterSpacing = 0.1.sp,
                        lineHeight = 24.sp
                    ),
                    maxLines = 2, //
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }


            Box(modifier = Modifier
                .height(300.dp)
                .clickable {
                    navController.navigate("heroDetail/${hero.id.toString()}")
                }
            ) {
                Image(
                    painter =
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = hero.thumbnail)
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                                placeholder(R.drawable.loading)
                            }).build()
                    ),
                    contentDescription = "Hero Image",
                    modifier = Modifier
                        .background(Color.White),

                    contentScale = ContentScale.Crop,

                    )
            }
        }
    }
}