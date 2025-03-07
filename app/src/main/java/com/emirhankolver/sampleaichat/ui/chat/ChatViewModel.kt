package com.emirhankolver.sampleaichat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhankolver.sampleaichat.data.local.dao.ChatsDao
import com.emirhankolver.sampleaichat.data.local.dao.MessagesDao
import com.emirhankolver.sampleaichat.data.local.entities.ChatEntity
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity
import com.emirhankolver.sampleaichat.domain.AIUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messagesDao: MessagesDao,
    private val chatsDao: ChatsDao,
    private val aiUseCase: AIUseCase,
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
            val userQuery = MessageEntity(
                message = textFieldValue.value,
                id = UUID.randomUUID().toString(),
                isUserCreated = true,
                chatId = chatId.value,
            )
            val serverResponse = MessageEntity(
                message = "",
                id = UUID.randomUUID().toString(),
                isUserCreated = false,
                chatId = chatId.value,
            )

            messagesDao.insert(serverResponse)
            delay(100)
            messagesDao.insert(userQuery)
            aiUseCase.postQuery(textFieldValue.value, serverResponse.id)
                .catch {
                    Log.e(TAG, "onTapSendButton: ", it)
                }.launchIn(viewModelScope)
            _textFieldValue.value = ""
        } catch (t: Throwable) {
            Log.e(TAG, "onTapSendButton: ", t)
        }
    }

    companion object {
        private const val TAG = "ChatViewModel"
    }

}