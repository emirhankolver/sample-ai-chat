package com.emirhankolver.sampleaichat.ui.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CapabilityCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    colorBackground: Color,
    colorForeground: Color,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(colorBackground)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = colorForeground,
                textAlign = TextAlign.Center,
            ),
        )
        Text(
            text = subtitle, color = colorForeground,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = colorForeground,
                textAlign = TextAlign.Center,
            ),
        )
    }
}