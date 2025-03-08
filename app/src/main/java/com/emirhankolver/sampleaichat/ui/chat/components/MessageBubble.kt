package com.emirhankolver.sampleaichat.ui.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity
import com.emirhankolver.sampleaichat.model.MessageBubbleStyle
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun MessageBubble(
    modifier: Modifier = Modifier,
    message: MessageEntity,
) {

    val style = MessageBubbleStyle.create(
        message,
        MaterialTheme.colorScheme,
    )

    if (message.message.isEmpty()) {
        return CircularProgressIndicator(
            modifier = modifier
                .padding(style.paddingValues)
                .size(36.dp)
        )
    }

    MarkdownText(
        modifier = modifier
            .padding(style.paddingValues)
            .fillMaxWidth()
            .clip(style.shape)
            .background(style.colorBackground)
            .padding(
                vertical = 12.dp,
                horizontal = 20.dp
            ),
        markdown = message.message,
        style = TextStyle.Default.copy(
            color = style.colorForeground,
        ),
    )
}