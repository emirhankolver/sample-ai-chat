package com.emirhankolver.sampleaichat.ui.home.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeHistoryScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(it)) {
            Text(text = "History Screen", modifier = Modifier.align(Alignment.Center))
        }
    }
}