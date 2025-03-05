package com.emirhankolver.sampleaichat.ui.chat

import android.icu.text.DateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhankolver.sampleaichat.data.local.dao.ChatsDao
import com.emirhankolver.sampleaichat.data.local.dao.MessagesDao
import com.emirhankolver.sampleaichat.data.local.entities.ChatEntity
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity
import com.emirhankolver.sampleaichat.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val _messageList = MutableStateFlow<UIState<List<MessageEntity>>>(UIState.Loading)
    val messageList: StateFlow<UIState<List<MessageEntity>>> = _messageList

    fun loadMessages(chatId: String) = viewModelScope.launch {
        this@ChatViewModel.chatId = chatId
        Log.d(TAG, "loadMessages(chatId: $chatId) called")
        try {
            _messageList.value = UIState.Success(messagesDao.getAll(chatId))
        } catch (t: Throwable) {
            _messageList.value = UIState.Error(t.message ?: "Unknown Error")
        }
    }

    fun onTextFieldValueChange(text: String) {
        _textFieldValue.value = (text)
    }

    fun onTapSendButton() = viewModelScope.launch(Dispatchers.IO) {
        if ((messageList.value !is UIState.Success) || chatId == null) {
            return@launch
        }
        try {
            if (!chatsDao.exists(chatId ?: "")) {
                chatsDao.insert(
                    ChatEntity(
                        id = chatId ?: "",
                        name = "New Chat", // TODO Chat name from Server.
                        updatedAt = System.currentTimeMillis(),
                    )
                )
            }
            val list = (_messageList.value as UIState.Success<List<MessageEntity>>)
                .data.toMutableList()
            val userQuery = MessageEntity(
                message = textFieldValue.value,
                id = UUID.randomUUID().toString(),
                isUserCreated = true,
                chatId = chatId!!,
            )
            val serverResponse = MessageEntity(
                message = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()),
                id = UUID.randomUUID().toString(),
                isUserCreated = false,
                chatId = chatId!!,
            )

            list.add(0, userQuery)
            list.add(0, serverResponse)

            messagesDao.insert(userQuery)
            messagesDao.insert(serverResponse)

            _messageList.value = UIState.Success(list.toList())
            _textFieldValue.value = ""
        } catch (t: Throwable) {
            _messageList.value = UIState.Error(t.message ?: "Unknown Error")
        }
    }

    companion object {
        private const val TAG = "ChatViewModel"
    }

}