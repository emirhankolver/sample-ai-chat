package com.emirhankolver.sampleaichat.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey
    val id: String,
    val chatId: String,
    val message: String,
    val isUserCreated: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
)