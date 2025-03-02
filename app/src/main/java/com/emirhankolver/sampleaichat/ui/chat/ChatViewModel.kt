package com.emirhankolver.sampleaichat.ui.chat

import androidx.lifecycle.ViewModel
import com.emirhankolver.sampleaichat.model.MessageDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    private val _textFieldValue = MutableStateFlow("")
    val textFieldValue: StateFlow<String> = _textFieldValue
    private val _messageList = MutableStateFlow(emptyList<MessageDetail>())
    val messageList: StateFlow<List<MessageDetail>> = _messageList

    fun onTextFieldValueChange(text: String) {
        _textFieldValue.value = (text)
    }

    fun onTapSendButton() {
        val list = _messageList.value.toMutableList()
        list.add(
            0, MessageDetail(
                message = textFieldValue.value,
                id = UUID.randomUUID().toString(),
                isUserCreated = true
            )
        )
        list.add(
            0, MessageDetail(
                message = "SNDMSNMNDMSNDMSNDMNSMDNSDMSN",
                id = UUID.randomUUID().toString(),
                isUserCreated = false
            )
        )
        _messageList.value = list.toList()
        _textFieldValue.value = ""
    }

}