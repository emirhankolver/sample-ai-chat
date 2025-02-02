package com.emirhankolver.sampleaichat.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emirhankolver.sampleaichat.R
import com.emirhankolver.sampleaichat.ui.home.HomeActivity
import com.emirhankolver.sampleaichat.ui.theme.SampleAIChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleAIChatTheme {
                SplashScreen()
            }
        }
    }

    @Composable
    private fun SplashScreen(viewModel: SplashViewModel = SplashViewModel()) {
        // Observe events from the ViewModel
        LaunchedEffect(viewModel) {
            viewModel.navigateHome.collect { event ->
                val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(intent)
                finish() // Close the splash activity
            }
        }

        // UI for the splash screen
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(size = 192.dp),
                        painter = painterResource(R.drawable.ic_app_logo),
                        contentDescription = "App Logo",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                    Text(
                        text = "SampleAI",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 40.sp
                        )
                    )
                }

                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(48.dp),
                )
            }
        }
    }
}