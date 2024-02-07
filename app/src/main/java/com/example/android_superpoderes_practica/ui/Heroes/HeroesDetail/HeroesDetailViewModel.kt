package com.example.android_superpoderes_practica.ui.Heroes.HeroesDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_superpoderes_practica.Data.Remote.Repository
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import com.example.android_superpoderes_practica.Domain.Model.HeroUIDetail
import com.example.android_superpoderes_practica.ui.Heroes.HeroesList.HeroesListViewModel
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
class HeroesDetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {


    private val _state: MutableStateFlow<HeroDetailState> = MutableStateFlow(HeroDetailState.Loading)
    val state: StateFlow<HeroDetailState> = _state.asStateFlow()


    fun getOneHero(id: Int?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _state.update { HeroDetailState.Loading }

                repository.getOneHeroListToRemote(id).collect {
                    val result = it

                    if (result.isNotEmpty()) {
                        _state.update { HeroDetailState.Success(result) }
                    } else {
                        _state.update { HeroDetailState.Error("Error") }
                    }
                }
            }
        }
    }


}

sealed class HeroDetailState {
    data class Success(val hero: List<HeroUIDetail>) : HeroDetailState()
    object Loading : HeroDetailState()
    data class Error(val error: String) : HeroDetailState()
}