package com.example.android_superpoderes_practica.ui.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_superpoderes_practica.dataa.Remote.Repository
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
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val state: StateFlow<LoginState> = _state.asStateFlow()


    fun launchLogin(userName: String, password: String) {
        viewModelScope.launch {
            _state.update { LoginState.Idle }

            val token = runCatching {
                withContext(Dispatchers.IO) {
                    repository.launchLogin(userName, password)
                }
            }
            if (token.isSuccess) {
                _state.update { LoginState.Success(token.getOrThrow()) }
            } else {
                _state.update { LoginState.Error(token.exceptionOrNull()?.message.orEmpty()) }
            }

        }
    }
}

sealed class LoginState {
    data class Success(val token: String) : LoginState()

    object Idle : LoginState()
    data class Error(val error: String) : LoginState()
}