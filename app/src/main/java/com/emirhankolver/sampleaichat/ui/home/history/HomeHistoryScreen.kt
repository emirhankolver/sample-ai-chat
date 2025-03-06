package com.emirhankolver.sampleaichat.ui.home.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhankolver.sampleaichat.R
import com.emirhankolver.sampleaichat.ui.chat.ChatActivity
import com.emirhankolver.sampleaichat.ui.components.EmptyListPlaceholder
import com.emirhankolver.sampleaichat.ui.components.SampleAlertDialog
import com.emirhankolver.sampleaichat.ui.home.history.components.HistoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHistoryScreen() {
    val viewModel = hiltViewModel<HistoryViewModel>()
    val listState = viewModel.chatListFlow.collectAsState()
    val dialogState = viewModel.deleteAllChatDialogFlow.collectAsState()
    val showSearchBar = viewModel.searchBarVisibilityFlow.collectAsState()
    val searchQuery = viewModel.searchQuery.collectAsState()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("History") },
                actions = {
                    if (listState.value.isEmpty()
                        && searchQuery.value.isEmpty()
                        && !showSearchBar.value) {
                        return@CenterAlignedTopAppBar
                    }
                    IconButton(onClick = viewModel::triggerSearchBar) {
                        val icon = if (showSearchBar.value) {
                            Icons.Rounded.Clear
                        } else {
                            Icons.Rounded.Search
                        }
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = icon,
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
            when {
                listState.value.isEmpty() && !showSearchBar.value -> {
                    EmptyListPlaceholder(
                        emoji = "📭",
                        title = "No Conversations Yet",
                        subtitle = "Start a chat and your history will appear here!",
                    )
                }
                listState.value.isEmpty() && showSearchBar.value -> {
                    EmptyListPlaceholder(
                        emoji = "🔍",
                        title = "No Matches Found",
                        subtitle = "Try a different keyword or check your spelling.",
                    )
                }
            }
            LazyColumn(Modifier.fillMaxSize()) {
                if (showSearchBar.value) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                        ) {
                            TextField(
                                searchQuery.value,
                                onValueChange = viewModel::updateSearchQuery,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text("Search Chat") },
                                prefix = {
                                    Icon(
                                        imageVector = Icons.Rounded.Search,
                                        contentDescription = "Search Icon",
                                        modifier = Modifier.padding(end = 8.dp)
                                    )
                                },
                                suffix = {
                                    if (searchQuery.value.isNotEmpty()) {
                                        Icon(
                                            imageVector = Icons.Rounded.Clear,
                                            contentDescription = "Clear Icon",
                                            modifier = Modifier
                                                .padding(0.dp)
                                                .clickable {
                                                    viewModel.updateSearchQuery("")
                                                },
                                        )
                                    }
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                )
                            )
                        }
                    }
                }
                items(
                    items = listState.value,
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