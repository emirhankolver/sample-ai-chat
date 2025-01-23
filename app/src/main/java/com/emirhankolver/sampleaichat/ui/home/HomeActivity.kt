package com.emirhankolver.sampleaichat.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.emirhankolver.sampleaichat.ui.home.account.HomeActivityScreen
import com.emirhankolver.sampleaichat.ui.theme.SampleAIChatTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleAIChatTheme {
                HomeActivityScreen()
            }
        }
    }
}