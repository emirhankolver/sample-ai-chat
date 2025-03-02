@file:OptIn(ExperimentalMaterial3Api::class)

package com.emirhankolver.sampleaichat.ui.chat

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhankolver.sampleaichat.ui.chat.components.CapabilitiesPlaceholder
import com.emirhankolver.sampleaichat.ui.chat.components.ChatTextInputBar
import com.emirhankolver.sampleaichat.ui.theme.SampleAIChatTheme

@Composable
fun ChatScreen() {
    SampleAIChatTheme {
        val context = LocalContext.current
        val viewModel = hiltViewModel<ChatViewModel>()
        val theme = MaterialTheme.colorScheme
        val colorBackground = theme.surfaceVariant
        val colorForeground = theme.onSurfaceVariant

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
                CapabilitiesPlaceholder(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .weight(1f),
                    colorBackground = colorBackground,
                    colorForeground = colorForeground,
                )
                ChatTextInputBar(
                    viewModel = viewModel,
                    colorBackground = colorBackground,
                    colorForeground = colorForeground,
                )
            }
        }
    }
}