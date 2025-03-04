package com.emirhankolver.sampleaichat.ui.chat

import android.icu.text.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhankolver.sampleaichat.data.local.dao.ChatsDao
import com.emirhankolver.sampleaichat.data.local.dao.MessagesDao
import com.emirhankolver.sampleaichat.data.local.entities.ChatEntity
import com.emirhankolver.sampleaichat.model.MessageDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagesDao: MessagesDao,
    private val chatsDao: ChatsDao,
) : ViewModel() {
    private var chatId: String? = null
    private val _textFieldValue = MutableStateFlow("")
    val textFieldValue: StateFlow<String> = _textFieldValue
    private val _messageList = MutableStateFlow(emptyList<MessageDetail>())
    val messageList: StateFlow<List<MessageDetail>> = _messageList

    fun loadMessages(chatId: String) = viewModelScope.launch {
        this@ChatViewModel.chatId = chatId
        chatsDao.insert(
            ChatEntity(
                id = chatId,
                name = "New Chat",
                updatedAt = System.currentTimeMillis(),
            )
        )
        _messageList.value = messagesDao.getAll(chatId).map {
            MessageDetail(
                message = it.message,
                id = it.id,
                isUserCreated = it.isUserCreated
            )
        }
    }

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
                message = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()),
                id = UUID.randomUUID().toString(),
                isUserCreated = false
            )
        )
        _messageList.value = list.toList()
        _textFieldValue.value = ""
    }

}