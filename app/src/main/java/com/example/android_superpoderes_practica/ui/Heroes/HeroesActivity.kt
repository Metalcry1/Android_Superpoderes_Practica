package com.example.android_superpoderes_practica.ui.Heroes

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.ui.theme.Android_Superpoderes_PracticaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

@AndroidEntryPoint
class HeroesActivity : ComponentActivity() {

    private val heroListViewModel: HeroesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_Superpoderes_PracticaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(heroListViewModel = heroListViewModel)
                }
            }
        }
    }
}

@Composable
private fun MainScreen(heroListViewModel: HeroesListViewModel) {

    val state by heroListViewModel.state.collectAsState()
    when (state) {
        is HeroesListViewModel.HeroListState.Success -> {
            HeroesListScreen(heros = (state as HeroesListViewModel.HeroListState.Success).heros)
        }

        else -> {

        }
    }
}


