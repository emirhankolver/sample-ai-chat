package com.emirhankolver.sampleaichat.domain

import com.emirhankolver.sampleaichat.data.local.dao.MessagesDao
import com.emirhankolver.sampleaichat.domain.ai.AIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AIUseCase @Inject constructor(
    private val repository: AIRepository,
    private val messagesDao: MessagesDao,
) {
    fun postQuery(
        prompt: String,
        messageId: String
    ): Flow<String> {
        return repository.postQuery(prompt)
    }
}