package com.emirhankolver.sampleaichat.ui.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatTextInputBar(
    colorBackground: Color,
    colorForeground: Color,
) {
    Column {
        HorizontalDivider()
        Row(
            Modifier.padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Ask me anything...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                        alpha = .25f,
                    ),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(
                        alpha = .5f,
                    ),
                    focusedPlaceholderColor = colorForeground,
                    unfocusedContainerColor = colorBackground,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedPlaceholderColor = colorForeground,
                )
            )
            IconButton(
                onClick = {},
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = colorBackground,
                    disabledContentColor = colorForeground,
                ),
                enabled = false,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(56.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Send,
                    contentDescription = null,
                )
            }
        }
    }
}