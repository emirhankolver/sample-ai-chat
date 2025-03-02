package com.emirhankolver.sampleaichat.ui.chat

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    private val _textFieldValue = MutableStateFlow("")
    val textFieldValue: StateFlow<String> = _textFieldValue

    fun onTextFieldValueChange(text: String) {
        _textFieldValue.value = (text)
    }

    fun onTapSendButton() {
        _textFieldValue.value = ""
    }

}