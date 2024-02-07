package com.example.android_superpoderes_practica.ui.Heroes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_superpoderes_practica.ui.Heroes.HeroesDetail.HeroDetailState
import com.example.android_superpoderes_practica.ui.Heroes.HeroesDetail.HeroesDetailScreen
import com.example.android_superpoderes_practica.ui.Heroes.HeroesDetail.HeroesDetailViewModel
import com.example.android_superpoderes_practica.ui.Heroes.HeroesList.HeroesListItem
import com.example.android_superpoderes_practica.ui.Heroes.HeroesList.HeroesListScreen
import com.example.android_superpoderes_practica.ui.Heroes.HeroesList.HeroesListViewModel
import com.example.android_superpoderes_practica.ui.theme.Android_Superpoderes_PracticaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeroesActivity : ComponentActivity() {

    private val heroesListViewModel: HeroesListViewModel by viewModels()
    private val heroesDetailViewModel: HeroesDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_Superpoderes_PracticaTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),

                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "heroList") {
                        composable("heroList") {
                            Log.w("HEROES", "PASO POR LIST")
                            HeroListMainScreen(
                                heroListViewModel = heroesListViewModel,
                                navController = navController
                            )
                        }

                        composable("heroDetail/{heroID}",
                            arguments = listOf(
                                navArgument("heroID") {
                                    this.type = NavType.StringType
                                    nullable = false
                                }
                            )
                        ) {
                            val id = it.arguments?.getString("heroID")

                            val idConvert = id?.toInt()
                            val result = heroesDetailViewModel.getOneHero(idConvert)
                            Log.w("HEROES", "PASO POR DETAIL")
                            HeroDetailMainScreen(
                                heroesDetailViewModel = heroesDetailViewModel,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun HeroListMainScreen(
        heroListViewModel: HeroesListViewModel,
        navController: NavController
    ) {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        val state by heroListViewModel.state.collectAsState()

        Scaffold(
            scaffoldState = scaffoldState,
            content = {
                when (state) {
                    is HeroesListViewModel.HeroListState.Success -> {
                        HeroesListScreen(
                            heros = (state as HeroesListViewModel.HeroListState.Success).heros,
                            modifier = Modifier
                                .padding(it)
                                .background(Color.Black),
                            navController = navController
                        )
                    }

                    else -> {

                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            heroListViewModel.insertMoreHeroes()
                            scaffoldState.snackbarHostState.showSnackbar("Cargando nuevos Heroes")

                        }
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add"
                        )
                    },
                    modifier = Modifier
                        .padding(16.dp)

                )
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = true,

            )
    }

    @Composable
    fun HeroDetailMainScreen(
        heroesDetailViewModel: HeroesDetailViewModel,
        navController: NavController
    ) {
        val scaffoldState = rememberScaffoldState()
        //val scope = rememberCoroutineScope()

        val state by heroesDetailViewModel.state.collectAsState()

        Scaffold(
            scaffoldState = scaffoldState,
            content = {
                when (state) {
                    is HeroDetailState.Success ->
                        HeroesDetailScreen(
                            hero = (state as HeroDetailState.Success).hero,
                            modifier = Modifier
                                .padding(it)
                                .background(Color.Black),
                            navController = navController
                        )

                    else -> {}
                }
            },

            )
    }
}



