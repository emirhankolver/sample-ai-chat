package com.emirhankolver.sampleaichat.ui.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatScreen()
        }
    }

    companion object {
        fun launch(context: Context, chatId: Int? = null) {
            context.startActivity(
                Intent(
                    context,
                    ChatActivity::class.java,
                )
            )
        }
    }
}