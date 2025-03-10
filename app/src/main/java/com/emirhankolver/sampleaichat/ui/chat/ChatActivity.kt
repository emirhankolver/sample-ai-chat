package com.emirhankolver.sampleaichat.ui.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatScreen(
                intent.getStringExtra(EXTRA_CHAT_ID)!!,
                intent.getStringExtra(EXTRA_CHAT_NAME)
            )
        }
    }

    companion object {
        private const val EXTRA_CHAT_ID: String = "EXTRA_CHAT_ID"
        private const val EXTRA_CHAT_NAME: String = "EXTRA_CHAT_NAME"

        fun startActivity(context: Context, chatId: String? = null, chatName: String? = null) {
            val intent = Intent(
                context,
                ChatActivity::class.java,
            )
            intent.putExtra(EXTRA_CHAT_ID, chatId ?: UUID.randomUUID().toString())
            intent.putExtra(EXTRA_CHAT_NAME, chatName)
            context.startActivity(
                intent
            )
        }
    }
}