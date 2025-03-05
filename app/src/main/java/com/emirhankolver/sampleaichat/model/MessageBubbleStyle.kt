package com.emirhankolver.sampleaichat.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity

data class MessageBubbleStyle(
    val colorBackground: Color,
    val colorForeground: Color,
    val paddingValues: PaddingValues,
    val shape: Shape,
) {
    companion object {
        fun create(detail: MessageEntity, colorScheme: ColorScheme): MessageBubbleStyle {
            return if (detail.isUserCreated) {
                MessageBubbleStyle(
                    colorScheme.primary,
                    colorScheme.onPrimary,
                    PaddingValues(start = 60.dp, end = 20.dp, bottom = 20.dp),
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = 12.dp,
                        bottomEnd = 4.dp
                    ),
                )
            } else {
                MessageBubbleStyle(
                    colorScheme.surfaceVariant,
                    colorScheme.onSurfaceVariant,
                    PaddingValues(start = 20.dp, end = 60.dp, bottom = 20.dp),
                    RoundedCornerShape(
                        topStart = 4.dp,
                        topEnd = 12.dp,
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    ),
                )
            }
        }
    }
}
