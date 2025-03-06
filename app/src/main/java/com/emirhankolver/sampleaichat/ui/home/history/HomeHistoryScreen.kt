package com.emirhankolver.sampleaichat.ui.home.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhankolver.sampleaichat.R
import com.emirhankolver.sampleaichat.model.UIState
import com.emirhankolver.sampleaichat.ui.chat.ChatActivity
import com.emirhankolver.sampleaichat.ui.components.EmptyListPlaceholder
import com.emirhankolver.sampleaichat.ui.components.ErrorCard
import com.emirhankolver.sampleaichat.ui.components.SampleAlertDialog
import com.emirhankolver.sampleaichat.ui.home.history.components.HistoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHistoryScreen() {
    val viewModel = hiltViewModel<HistoryViewModel>()
    val listState = viewModel.chatListFlow.collectAsState()
    val dialogState = viewModel.deleteAllChatDialogFlow.collectAsState()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("History") },
                actions = {
                    when (val state = listState.value) {
                        is UIState.Success -> {
                            if (state.data.isEmpty()) return@CenterAlignedTopAppBar
                            IconButton(onClick = {}) {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "Search Icon",
                                )
                            }
                            IconButton(onClick = viewModel::showDialog) {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(R.drawable.ic_delete_outlined),
                                    contentDescription = "Delete Icon",
                                )
                            }
                        }

                        else -> {}
                    }
                },
            )
        },
    ) {
        if (dialogState.value) {
            SampleAlertDialog(
                title = "Delete All Conversations",
                subtitle = "Are you sure you want to delete all history?",
                positiveButtonText = "Yes",
                negativeButtonText = "No",
                onDismissRequest = viewModel::closeDialog,
                onPositiveClick = viewModel::deleteAllChats,
            )
        }

        Box(
            Modifier
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center,
        ) {
            when (val state = listState.value) {
                is UIState.Error -> {
                    ErrorCard(
                        subtitle = state.message,
                        onTapRetry = viewModel::loadChatList
                    )
                }

                is UIState.Loading -> {
                    CircularProgressIndicator()
                }

                is UIState.Success -> {
                    if (state.data.isEmpty()) {
                        EmptyListPlaceholder(
                            emoji = "📭",
                            title = "No Conversations Yet",
                            subtitle = "Start a chat and your history will appear here!",
                        )
                    }
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(
                            items = state.data,
                            key = { item -> item.id }
                        ) { history ->
                            HistoryCard(history) { selectedHistory ->
                                ChatActivity.startActivity(context, selectedHistory.id)
                            }
                        }
                    }
                }
            }
        }
    }
}