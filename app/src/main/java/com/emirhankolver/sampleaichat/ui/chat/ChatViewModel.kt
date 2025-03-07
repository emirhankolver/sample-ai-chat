package com.emirhankolver.sampleaichat.ui.chat

import android.icu.text.DateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhankolver.sampleaichat.data.local.dao.ChatsDao
import com.emirhankolver.sampleaichat.data.local.dao.MessagesDao
import com.emirhankolver.sampleaichat.data.local.entities.ChatEntity
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagesDao: MessagesDao,
    private val chatsDao: ChatsDao,
) : ViewModel() {
    private val chatId = MutableStateFlow("")
    private val _textFieldValue = MutableStateFlow("")
    val textFieldValue: StateFlow<String> = _textFieldValue
    val messageList = chatId.flatMapLatest {
        messagesDao.getAll(it)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun loadMessages(chatId: String)  {
        this@ChatViewModel.chatId.value = chatId
    }

    fun onTextFieldValueChange(text: String) {
        _textFieldValue.value = (text)
    }

    fun onTapSendButton() = viewModelScope.launch(Dispatchers.IO) {
        if (chatId.value.isEmpty()) return@launch
        try {
            if (!chatsDao.exists(chatId.value)) {
                chatsDao.insert(
                    ChatEntity(
                        id = chatId.value,
                        name = textFieldValue.value.take(30), // TODO Chat name from Server.
                        updatedAt = System.currentTimeMillis(),
                    )
                )
            }
            val list = messageList.value.toMutableList()
            val userQuery = MessageEntity(
                message = textFieldValue.value,
                id = UUID.randomUUID().toString(),
                isUserCreated = true,
                chatId = chatId.value,
            )
            val serverResponse = MessageEntity(
                message = DateFormat.getDateTimeInstance().format(System.currentTimeMillis()),
                id = UUID.randomUUID().toString(),
                isUserCreated = false,
                chatId = chatId.value,
            )

            list.add(0, userQuery)
            list.add(0, serverResponse)

            messagesDao.insert(userQuery)
            delay(250)
            messagesDao.insert(serverResponse)

            _textFieldValue.value = ""
        } catch (t: Throwable) {
            Log.e(TAG, "onTapSendButton: ", t)
        }
    }

    companion object {
        private const val TAG = "ChatViewModel"
    }

}