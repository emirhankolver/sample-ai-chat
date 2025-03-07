package com.emirhankolver.sampleaichat.ui.home.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirhankolver.sampleaichat.R
import com.emirhankolver.sampleaichat.ui.home.account.components.AccountOptionRow
import com.emirhankolver.sampleaichat.ui.home.account.components.FeatureCard
import com.emirhankolver.sampleaichat.ui.home.account.components.TitleDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAccountScreen() {
    val viewModel = hiltViewModel<AccountViewModel>()
    val scrollState = rememberScrollState()

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
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.profile_picture),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(72.dp)
                        .clip(CircleShape),
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
                iconStart = painterResource(R.drawable.ic_person_outlined),
            )
            AccountOptionRow(
                title = "Security",
                iconStart = painterResource(R.drawable.ic_security_outlined),
            )
            AccountOptionRow(
                title = "Language",
                iconStart = painterResource(R.drawable.ic_text_outlined),
            )
            AccountOptionRow(
                title = "Dark Mode",
                iconStart = painterResource(R.drawable.ic_dark_mode_outlined),
            )
            TitleDivider("About")
            AccountOptionRow(
                title = "Help Center",
                iconStart = painterResource(R.drawable.ic_text_outlined),
            )
            AccountOptionRow(
                title = "Privacy Policy",
                iconStart = painterResource(R.drawable.ic_privacy_policy_outlined),
            )
            AccountOptionRow(
                title = "About SampleAI",
                iconStart = painterResource(R.drawable.ic_info_outlined),
            )
            AccountOptionRow(
                title = "Logout",
                iconStart = painterResource(R.drawable.ic_logout_outlined),
            )
        }
    }
}