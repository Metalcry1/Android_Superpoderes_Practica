package com.example.android_superpoderes_practica.ui.Main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_superpoderes_practica.ui.Comics.ComicListState
import com.example.android_superpoderes_practica.ui.Comics.ComicsViewModel
import com.example.android_superpoderes_practica.ui.HeroesDetail.HeroDetailState
import com.example.android_superpoderes_practica.ui.HeroesDetail.HeroesDetailScreen
import com.example.android_superpoderes_practica.ui.HeroesDetail.HeroesDetailViewModel
import com.example.android_superpoderes_practica.ui.HeroesList.HeroesListViewModel
import com.example.android_superpoderes_practica.ui.theme.Android_Superpoderes_PracticaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val heroesListViewModel: HeroesListViewModel by viewModels()
    private val heroesDetailViewModel: HeroesDetailViewModel by viewModels()
    private val comicsViewModel: ComicsViewModel by viewModels()

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
                            MainScreen(
                                heroesListViewModel = heroesListViewModel,
                                comicsViewModel = comicsViewModel,
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
    fun MainScreen(
        heroesListViewModel: HeroesListViewModel,
        comicsViewModel: ComicsViewModel,
        navController: NavController
    ) {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val stateHero by heroesListViewModel.stateHero.collectAsState()
        val stateComic by comicsViewModel.stateComic.collectAsState()
        var showingHeroesOrComics by remember { mutableStateOf(true) }

        Scaffold(
            scaffoldState = scaffoldState,
            contentColor = Color.Black,
            backgroundColor = Color.Black,
            bottomBar = {
                BotomBar(
                    onHeroesClick = { showingHeroesOrComics = true },
                    onComicsclick = { showingHeroesOrComics = false }
                )
            },
            content = {
                if (showingHeroesOrComics) {

                when (stateHero) {
                    is HeroesListViewModel.HeroListState.Success -> {
                        HeroList(
                            heros = (stateHero as HeroesListViewModel.HeroListState.Success).heros,
                            modifier = Modifier
                                .padding(it)
                                .background(Color.Black),
                            navController = navController
                        )
                    }

                    else -> {

                    }
                }
            }else{

                when(stateComic){
                    is ComicListState.Success -> {
                        ComicsList(
                            comics = (stateComic as ComicListState.Success).comics,
                            modifier = Modifier
                                .padding(4.dp)
                                .background(Color.Black),
                            navController = navController
                        )

                    }
                    else -> {

                    }

                }



                }
            },

            floatingActionButton = {
                if (showingHeroesOrComics) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 64.dp, end = 16.dp)
                    ) {
                        FloatingActionButton(
                            onClick = {
                                scope.launch {
                                    heroesListViewModel.insertMoreHeroes()
                                    scaffoldState.snackbarHostState.showSnackbar("Cargando nuevos Heroes")
                                }
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add"
                                )
                            }
                        )
                    }
                }
            }

,
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = true
        )
    }


    @Composable
    fun BotomBar(
        onHeroesClick: () -> Unit,
        onComicsclick: () -> Unit
    ) {
        BottomAppBar(backgroundColor = Color.Red) {
            NavigationBarItem(
                selected = false,
                onClick = onHeroesClick,
                alwaysShowLabel = true,
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                        ){

                        Text(
                            text = "Heroes List",
                            color = Color.Black
                        )

                        Icon(
                            tint = Color.Black,
                            imageVector = Icons.Default.AllInbox,
                            contentDescription = "Heroes List"
                        )
                    }
                }
            )
            NavigationBarItem(
                selected = false,
                onClick = onComicsclick,
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){

                        Text(
                            text = "Comics List",
                            color = Color.Black
                        )

                        Icon(
                            tint = Color.Black,
                            imageVector = Icons.Default.AllInbox,
                            contentDescription = "Comics List"
                        )
                    }
                }
            )
        }
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
            contentColor = Color.Black,
            backgroundColor = Color.Black,
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




