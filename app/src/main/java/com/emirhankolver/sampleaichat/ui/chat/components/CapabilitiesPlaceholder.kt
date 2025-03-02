package com.emirhankolver.sampleaichat.ui.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.emirhankolver.sampleaichat.R

@Composable
fun CapabilitiesPlaceholder(
    modifier: Modifier = Modifier,
    colorBackground: Color,
    colorForeground: Color,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .padding(24.dp)
                .size(72.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_app_logo),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorForeground)
        )
        Text(
            text = "Capabilities",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = colorForeground,
            )
        )
        CapabilityCard(
            modifier = Modifier.padding(top = 24.dp),
            title = "Answer all your questions.",
            subtitle = "(Just ask me anything you like!)",
            colorBackground = colorBackground,
            colorForeground = colorForeground,
        )
        CapabilityCard(
            modifier = Modifier.padding(top = 16.dp),
            title = "Generate all the text you want.",
            subtitle = "(essays, articles, reports, stories & more.)",
            colorBackground = colorBackground,
            colorForeground = colorForeground,
        )
        CapabilityCard(
            modifier = Modifier.padding(top = 16.dp),
            title = "Conversational AI",
            subtitle = "(I can talk to you like a natural human.)",
            colorBackground = colorBackground,
            colorForeground = colorForeground,
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "These are few examples of what I can do.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = colorForeground,
            ),
        )
    }
}