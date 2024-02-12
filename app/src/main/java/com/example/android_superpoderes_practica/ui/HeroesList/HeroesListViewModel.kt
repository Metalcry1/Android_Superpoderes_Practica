package com.example.android_superpoderes_practica.ui.HeroesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_superpoderes_practica.dataa.Remote.Repository
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _stateHero: MutableStateFlow<HeroListState> = MutableStateFlow(HeroListState.Loading)
    val stateHero: StateFlow<HeroListState> = _stateHero.asStateFlow()


    init {
        getHerosList()
    }


    fun getHerosList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _stateHero.update { HeroListState.Loading }

                repository.getHeroList(0).collect {
                    val result = it

                    if (result.isNotEmpty()) {
                        _stateHero.update { HeroListState.Success(result) }
                    } else {
                        _stateHero.update { HeroListState.Error("Error") }
                    }
                }
            }
        }

    }

    suspend fun insertMoreHeroes() {
        viewModelScope.launch {
            val listaOffsets = listOf(10, 20)
            val offset = listaOffsets.random()
            withContext(Dispatchers.IO) {
                repository.insertMoreHeroes(offset)
                getHerosList()
            }

        }

    }

    fun randomOffset(): Int {
        val listaOffsets = listOf(0, 10, 20)
        return listaOffsets.random()
    }

    sealed class HeroListState {
        data class Success(val heros: List<HeroUI>) : HeroListState()
        object Loading : HeroListState()
        data class Error(val error: String) : HeroListState()
    }
}

