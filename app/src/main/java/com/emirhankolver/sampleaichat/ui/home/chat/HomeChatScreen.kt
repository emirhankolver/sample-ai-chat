package com.emirhankolver.sampleaichat.ui.home.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.emirhankolver.sampleaichat.R
import com.emirhankolver.sampleaichat.ui.chat.ChatActivity

@Composable
fun HomeChatScreen() {
    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_app_logo),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(top = 36.dp, start = 48.dp, end = 48.dp),
                text = "Say Hello to SampleAI",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier.padding(top = 36.dp),
                text = "Chat instantly with SampleAI.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Ask me anything, anytime!",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 36.dp),
                onClick = {
                    ChatActivity.startActivity(context)
                },
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("Start Chat")
            }
        }
    }
}

