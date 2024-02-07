package com.example.android_superpoderes_practica.ui.Heroes.HeroesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_superpoderes_practica.Data.Remote.Repository
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

    private val _state: MutableStateFlow<HeroListState> = MutableStateFlow(HeroListState.Loading)
    val state: StateFlow<HeroListState> = _state.asStateFlow()


    init {
        getHerosList()
    }


    fun getHerosList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _state.update { HeroListState.Loading }

                repository.getHeroList(randomOffset()).collect {
                    val result = it

                    if (result.isNotEmpty()) {
                        _state.update { HeroListState.Success(result) }
                    } else {
                        _state.update { HeroListState.Error("Error") }
                    }
                }
            }
        }

    }

    suspend fun insertMoreHeroes() {
        viewModelScope.launch {
            val listaOffsets = listOf(21, 41)
            val offset = listaOffsets.random()
            withContext(Dispatchers.IO) {
                repository.insertMoreHeroes(offset)
                getHerosList()
            }

        }

    }

    fun randomOffset(): Int {
        val listaOffsets = listOf(0, 21, 41)
        return listaOffsets.random()
    }

    sealed class HeroListState {
        data class Success(val heros: List<HeroUI>) : HeroListState()
        object Loading : HeroListState()
        data class Error(val error: String) : HeroListState()
    }
}

