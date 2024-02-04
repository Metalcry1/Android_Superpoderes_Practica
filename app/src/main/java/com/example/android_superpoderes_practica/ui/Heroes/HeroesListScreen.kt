package com.example.android_superpoderes_practica.ui.Heroes

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import com.example.android_superpoderes_practica.R

@Composable
fun HeroesListScreen(heros: List<HeroUI>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()){
        items(heros){
            HeroesListItem(hero = it)
        }
    }
}

@Composable
fun HeroesListItem(hero: HeroUI, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        var loading by remember {
            mutableStateOf(true)
        }

        var error by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Text(
                text = hero.name,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

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
                    .width(1000.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )



        }
    }
}
