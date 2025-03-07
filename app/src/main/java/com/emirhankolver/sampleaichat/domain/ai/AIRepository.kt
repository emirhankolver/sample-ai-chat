package com.emirhankolver.sampleaichat.domain.ai

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AIRepository @Inject constructor(
    private val dataSource: AIDataSource
) {
    fun postQuery(
        prompt: String,
    ): Flow<String> {
        return dataSource.postQuery(prompt)
    }
}