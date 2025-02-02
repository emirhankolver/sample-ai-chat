package com.emirhankolver.sampleaichat.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _navigateHome = MutableSharedFlow<Boolean>();
    val navigateHome: SharedFlow<Boolean> = _navigateHome

    init {
        viewModelScope.launch {
            delay(2000)
            _navigateHome.emit(true);
        }
    }


}