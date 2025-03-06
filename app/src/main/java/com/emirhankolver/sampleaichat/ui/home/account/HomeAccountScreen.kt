package com.emirhankolver.sampleaichat.ui.home.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhankolver.sampleaichat.ui.home.account.components.AccountOptionRow
import com.emirhankolver.sampleaichat.ui.home.account.components.FeatureCard
import com.emirhankolver.sampleaichat.ui.home.account.components.TitleDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAccountScreen() {
    val viewModel = hiltViewModel<AccountViewModel>()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Account") },
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.AccountCircle,
                    contentDescription = "Person Icon",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(72.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Column(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        "Emirhan Kolver",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        "emirhanklvr@gmail.com",
                        style = MaterialTheme.typography.bodyMedium,
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
            FeatureCard(
                title = "Upgrade to PRO!",
                subtitle = "Enjoy all benefits without restrictions!",
                icon = Icons.Rounded.Star,
            )
            TitleDivider("General")
            AccountOptionRow(
                title = "Personal Info",
                iconStart = Icons.Outlined.Person,
            )
            AccountOptionRow(
                title = "Security",
                iconStart = Icons.Outlined.Person,
            )
            AccountOptionRow(
                title = "Language",
                iconStart = Icons.Outlined.Person,
            )
            AccountOptionRow(
                title = "Dark Mode",
                iconStart = Icons.Outlined.Person,
            )
            TitleDivider("About")
            AccountOptionRow(
                title = "Help Center",
                iconStart = Icons.Outlined.Person,
            )
            AccountOptionRow(
                title = "Privacy Policy",
                iconStart = Icons.Outlined.Person,
            )
            AccountOptionRow(
                title = "About SampleAI",
                iconStart = Icons.Outlined.Person,
            )
            AccountOptionRow(
                title = "Logout",
                iconStart = Icons.Outlined.Person,
            )
        }
    }
}