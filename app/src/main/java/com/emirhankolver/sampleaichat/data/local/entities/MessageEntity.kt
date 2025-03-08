package com.emirhankolver.sampleaichat.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("chatId")
    val chatId: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("isUserCreated")
    val isUserCreated: Boolean,
    @SerializedName("timestamp")
    val timestamp: Long = System.currentTimeMillis(),
)