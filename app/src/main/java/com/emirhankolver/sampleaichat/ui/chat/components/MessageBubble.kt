package com.emirhankolver.sampleaichat.ui.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity
import com.emirhankolver.sampleaichat.model.MessageBubbleStyle

@Composable
fun MessageBubble(
    modifier: Modifier = Modifier,
    message: MessageEntity,
) {
    val style = MessageBubbleStyle.create(
        message,
        MaterialTheme.colorScheme,
    )

    Text(
        modifier = modifier
            .padding(style.paddingValues)
            .fillMaxWidth()
            .clip(style.shape)
            .background(style.colorBackground)
            .padding(
                vertical = 12.dp,
                horizontal = 20.dp
            ),
        text = message.message,
        color = style.colorForeground,
    )
}