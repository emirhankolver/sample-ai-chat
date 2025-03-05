package com.emirhankolver.sampleaichat.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val updatedAt: Long,
)