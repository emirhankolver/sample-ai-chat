package com.emirhankolver.sampleaichat.ui.home.history

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhankolver.sampleaichat.R
import com.emirhankolver.sampleaichat.model.UIState
import com.emirhankolver.sampleaichat.ui.components.ErrorCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHistoryScreen() {
    val viewModel = hiltViewModel<HistoryViewModel>()
    val listState = viewModel.chatListFlow.collectAsState()

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
                            IconButton(onClick = {}) {
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
        Box(
            Modifier
                .padding(top = it.calculateTopPadding())
                .padding(horizontal = 20.dp)
        ) {
            when (val state = listState.value) {
                is UIState.Error -> {
                    ErrorCard(
                        subtitle = state.message
                    ) { }
                }

                is UIState.Loading -> {
                    CircularProgressIndicator()
                }

                is UIState.Success -> {
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(
                            items = state.data,
                            key = { item -> item.id }
                        ) {
                            Card(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                colors = CardColors(
                                    contentColor = MaterialTheme.colorScheme.onSurface,
                                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                                    disabledContentColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent,
                                ),
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Column(
                                        Modifier
                                            .padding(12.dp)
                                            .weight(1f)
                                    ) {
                                        BasicText(
                                            it.name,
                                            style = MaterialTheme.typography.titleMedium,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                        BasicText(
                                            DateFormat.getDateTimeInstance().format(it.updatedAt),
                                            style = MaterialTheme.typography.bodySmall,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                    }
                                    IconButton(onClick = {}) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                            contentDescription = "Navigate Icon",
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}