package com.emirhankolver.sampleaichat.ui.home.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhankolver.sampleaichat.data.local.dao.ChatsDao
import com.emirhankolver.sampleaichat.data.local.dao.MessagesDao
import com.emirhankolver.sampleaichat.data.local.entities.ChatEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val chatsDao: ChatsDao,
    private val messagesDao: MessagesDao,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val chatListFlow: StateFlow<List<ChatEntity>> = _searchQuery
        .flatMapLatest { query -> chatsDao.search(query) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    private val _deleteAllChatDialogFlow = MutableStateFlow(false)
    val deleteAllChatDialogFlow: StateFlow<Boolean> = _deleteAllChatDialogFlow
    private val _searchBarVisibilityFlow = MutableStateFlow(false)
    val searchBarVisibilityFlow: StateFlow<Boolean> = _searchBarVisibilityFlow

    fun deleteAllChats() = viewModelScope.launch(Dispatchers.IO) {
        chatsDao.deleteAll()
        messagesDao.deleteAll()
    }

    fun showDialog() {
        _deleteAllChatDialogFlow.value = true
    }

    fun closeDialog() {
        _deleteAllChatDialogFlow.value = false
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun triggerSearchBar() {
        _searchQuery.value = ""
        _searchBarVisibilityFlow.value = !_searchBarVisibilityFlow.value
    }


}