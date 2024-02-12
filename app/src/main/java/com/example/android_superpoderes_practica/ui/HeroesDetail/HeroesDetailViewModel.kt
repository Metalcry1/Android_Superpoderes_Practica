package com.example.android_superpoderes_practica.ui.HeroesDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_superpoderes_practica.dataa.Remote.Repository
import com.example.android_superpoderes_practica.Domain.Model.HeroUIDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface FavoriteStateListener {
    fun onFavoriteChanged(heroId: String, isFavorite: Boolean)
}

@HiltViewModel
class HeroesDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(), FavoriteStateListener {


    private val _state: MutableStateFlow<HeroDetailState> =
        MutableStateFlow(HeroDetailState.Loading)
    val state: StateFlow<HeroDetailState> = _state.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite


    fun getOneHero(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _state.update { HeroDetailState.Loading }

                repository.getOneHeroListToRemote(id).collect {
                    val result = it

                    if (result.toString().isNotEmpty()) {
                        _state.update { HeroDetailState.Success(result) }
                    } else {
                        _state.update { HeroDetailState.Error("Error") }
                    }
                }
            }
        }
    }

    override fun onFavoriteChanged(heroId: String, isFavorite: Boolean) {
        _isFavorite.value = isFavorite
        val heroIdString = heroId
        val heroIdLong = heroIdString.toLong()

        viewModelScope.launch {
            val heroDB = withContext(Dispatchers.IO) {
                repository.updateFavoriteStatus(heroIdLong, isFavorite)
            }
        }
    }

    fun getHeroStatusFavourite(heroId: Long) {

        viewModelScope.launch {

            val isFavoriteResult = withContext(Dispatchers.IO) {

                repository.getHeroStatusFavourite(heroId)
            }
            _isFavorite.value = isFavoriteResult
        }
    }
}

sealed class HeroDetailState {
    data class Success(val hero: HeroUIDetail) : HeroDetailState()
    object Loading : HeroDetailState()
    data class Error(val error: String) : HeroDetailState()
}