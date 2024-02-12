package com.example.android_superpoderes_practica.ui.Comics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_superpoderes_practica.dataa.Remote.Repository
import com.example.android_superpoderes_practica.Domain.Model.ComicUI
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
class ComicsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _stateComic: MutableStateFlow<ComicListState> = MutableStateFlow(ComicListState.Loading)
    val stateComic: StateFlow<ComicListState> = _stateComic.asStateFlow()

    init {
        getComicList()
    }

    fun getComicList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _stateComic.update { ComicListState.Loading }

                repository.getComicsListToRemote(randomOffset()).collect {
                    val result = it

                    if (result.isNotEmpty()) {
                        _stateComic.update { ComicListState.Success(result) }
                    } else {
                        _stateComic.update { ComicListState.Error("Error") }
                    }
                }
            }
        }

    }

    fun randomOffset(): Int {
        val listaOffsets = listOf(0, 21, 41)
        return listaOffsets.random()
    }

}
    sealed class ComicListState {
        data class Success(val comics: List<ComicUI>) : ComicListState()
        object Loading : ComicListState()
        data class Error(val error: String) : ComicListState()
    }



