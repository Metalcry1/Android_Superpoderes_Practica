package com.example.android_superpoderes_practica.ui.HeroesDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddToQueue
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.android_superpoderes_practica.Domain.Model.ComicsItem
import com.example.android_superpoderes_practica.Domain.Model.HeroUIDetail
import com.example.android_superpoderes_practica.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesDetailScreen(
    hero: List<HeroUIDetail>,
    modifier: Modifier = Modifier,
    navController: NavController
) {

    var showingComics by remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier,
        containerColor = Color.Black,
        contentColor= Color.Black,

        bottomBar = {
            BotomBar(
                onComicsClick = { showingComics = true },
                onSeriesClick = { showingComics = false }
            )
        },
        content = { paddingValues ->
            val currentHero = hero.first()
            val itemsToShow = if (showingComics) currentHero.comics.items else currentHero.series.items

            HeroesDetailItem(
                hero = currentHero,
                itemsToShow = itemsToShow,
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
    )
}

@Composable
fun BotomBar(
    onComicsClick: () -> Unit,
    onSeriesClick: () -> Unit
) {
    BottomAppBar(backgroundColor = Color.Red.copy(alpha = 0.5F)) {
        NavigationBarItem(
            selected = false,
            onClick = onComicsClick,
            icon = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    Text(
                        text = "Comics",
                        color = Color.Black
                    )

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.AllInbox,
                        contentDescription = "Comics"
                    )
                }
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = onSeriesClick,
            icon = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    Text(
                        text = "Series",
                        color = Color.Black
                    )

                    Icon(
                        tint = Color.Black,
                        imageVector = Icons.Default.AllInbox,
                        contentDescription = "Series"
                    )
                }
            }
        )
    }
}

@Composable
fun HeroesDetailItem(
    hero: HeroUIDetail,
    itemsToShow: List<ComicsItem>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(modifier = modifier) {

        Text(
            text = hero.name, fontSize = 25.sp, color = Color.White, style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal,
                letterSpacing = 0.1.sp,
                lineHeight = 24.sp
            ), modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Box(modifier = Modifier
            .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = hero.convertThumbnailToString)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                            placeholder(R.drawable.loading)
                        }).build()
                ),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .background(Color.Black)
                    .height(300.dp)
                    .fillMaxWidth(),

                contentScale = ContentScale.Fit,
            )
        }

        val titleText = if (itemsToShow == hero.comics.items) "COMICS" else "SERIES"
        Text(
            text = titleText,
            fontSize = 25.sp,
            color = Color.White,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal,
                letterSpacing = 0.1.sp,
                lineHeight = 24.sp
            ), modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            itemsToShow.forEach { item ->
                item.name?.let {
                    Column(
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = it,
                            fontSize = 20.sp,
                            color = Color.White,
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                fontStyle = FontStyle.Normal,
                                letterSpacing = 0.1.sp,
                                lineHeight = 24.sp
                            ),
                            modifier = Modifier.align(Alignment.Start)
                        )
                    }
                }
            }
        }

    }
}
