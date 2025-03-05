@file:OptIn(ExperimentalMaterial3Api::class)

package com.emirhankolver.sampleaichat.ui.chat

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity
import com.emirhankolver.sampleaichat.model.UIState
import com.emirhankolver.sampleaichat.ui.chat.components.CapabilitiesPlaceholder
import com.emirhankolver.sampleaichat.ui.chat.components.ChatTextInputBar
import com.emirhankolver.sampleaichat.ui.chat.components.MessageBubble
import com.emirhankolver.sampleaichat.ui.components.ErrorCard
import com.emirhankolver.sampleaichat.ui.theme.SampleAIChatTheme

@Composable
fun ChatScreen(chatId: String) {
    SampleAIChatTheme {
        val context = LocalContext.current
        val listState = rememberLazyListState()
        val viewModel = hiltViewModel<ChatViewModel>()
        val theme = MaterialTheme.colorScheme
        val colorBackground = theme.surfaceVariant
        val colorForeground = theme.onSurfaceVariant
        val messageList = viewModel.messageList.collectAsState()

        LaunchedEffect(Unit) {
           viewModel.loadMessages(chatId)
        }

        LaunchedEffect(messageList.value.hashCode()) {
            listState.animateScrollToItem(0)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("New Chat")
                    },
                    navigationIcon = {
                        IconButton(onClick = { (context as ComponentActivity?)?.finish() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                when (messageList.value) {
                    is UIState.Error -> {
                        Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            ErrorCard(
                                subtitle = (messageList.value as UIState.Error).message,
                                onTapRetry = { viewModel.loadMessages(chatId) }
                            )
                        }
                    }

                    is UIState.Loading -> {
                        Box(
                            Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is UIState.Success -> {
                        val list = (messageList.value as UIState.Success<List<MessageEntity>>).data
                        if (list.isEmpty()) {
                            CapabilitiesPlaceholder(
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .weight(1f),
                                colorBackground = colorBackground,
                                colorForeground = colorForeground,
                            )
                        } else {
                            LazyColumn(
                                modifier = Modifier.weight(1f),
                                reverseLayout = true,
                                state = listState,
                            ) {
                                items(
                                    items = list,
                                    key = { message -> message.id },
                                    itemContent = { message ->
                                        MessageBubble(
                                            message = message,
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
                ChatTextInputBar(
                    viewModel = viewModel,
                    colorBackground = colorBackground,
                    colorForeground = colorForeground,
                )
            }
        }
    }
}