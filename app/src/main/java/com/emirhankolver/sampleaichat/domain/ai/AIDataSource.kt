package com.emirhankolver.sampleaichat.domain.ai

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AIDataSource @Inject constructor(
    private val aiService: AIService,
) {
    fun postQuery(
        prompt: String
    ): Flow<String> {
        return aiService.postQuery(prompt)
    }
}