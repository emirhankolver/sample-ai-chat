package com.emirhankolver.sampleaichat.ui.home.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhankolver.sampleaichat.data.local.dao.ChatsDao
import com.emirhankolver.sampleaichat.data.local.entities.ChatEntity
import com.emirhankolver.sampleaichat.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val chatsDao: ChatsDao
) : ViewModel() {

    private val _chatListFlow = MutableStateFlow<UIState<List<ChatEntity>>>(UIState.Loading)
    val chatListFlow: StateFlow<UIState<List<ChatEntity>>> = _chatListFlow

    init {
        loadChatList()
    }

     fun loadChatList() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _chatListFlow.value = UIState.Success(chatsDao.getAll())
        } catch (t:Throwable) {
            _chatListFlow.value = UIState.Error(t.message ?: "Unknown Error")
        }
    }
}