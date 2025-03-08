package com.emirhankolver.sampleaichat.model

import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity
import com.google.gson.annotations.SerializedName

data class QueryRequest(
    @SerializedName("prompt")
    private val prompt: String,
    @SerializedName("messageHistory")
    private val messageHistory: List<MessageEntity> = emptyList(),
)
