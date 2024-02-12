package com.example.android_superpoderes_practica.ui.Main

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.android_superpoderes_practica.Domain.Model.ComicUI
import com.example.android_superpoderes_practica.R
import java.text.SimpleDateFormat

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComicsListItem(comic: ComicUI, modifier: Modifier = Modifier, navController: NavController, ) {

    var isComicDialogShow by rememberSaveable { mutableStateOf(false) }
    Log.w("COMIC", comic.id.toString())
    Card(
        modifier = modifier
            .fillMaxWidth(),
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
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Red.copy(alpha = 0.5F))
        ) {

            Text(
                text = comic.title,
                fontSize = 25.sp,
                color = Color.Black,
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Normal,
                    letterSpacing = 0.1.sp,
                    lineHeight = 24.sp
                ),
                modifier = Modifier

                    .fillMaxSize()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Box(modifier = Modifier
                .clickable {
                    isComicDialogShow = true
                }
            ) {
                Image(
                    painter =
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = comic.thumbnailComics)
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                                placeholder(R.drawable.loading)
                            }).build()
                    ),
                    contentDescription = "Hero Image",
                    modifier = Modifier
                        .background(Color.White)
                        .height(300.dp)
                        .fillMaxWidth(),

                    contentScale = ContentScale.Crop,

                    )
            }
            ComicDialog(comic,isComicDialogShow) { isComicDialogShow = false }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComicDialog(
    comic: ComicUI,
    isComicDialogShow: Boolean,
    onDismiss:()-> Unit
) {

    val dateString = comic.modified
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXX")
    val date = dateFormat.parse(dateString)
    val dayMonthYearFormat = SimpleDateFormat("dd-MM-yyyy")
    val formatDate = dayMonthYearFormat.format(date)

    if (isComicDialogShow) {

        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Volver")

                }
            },
            title = { Text(text = "Mas detalles") },
            text = {
                Column {
                    Text(text = "ID: ${comic.id} ")
                    Text(text = "Ultima modificacion: $formatDate")
                }
            }

        )
    }
}