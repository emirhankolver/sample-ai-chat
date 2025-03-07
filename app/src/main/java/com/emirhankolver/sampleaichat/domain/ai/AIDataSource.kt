package com.emirhankolver.sampleaichat.domain.ai

import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class AIDataSource @Inject constructor(
    private val aiService: AIService,
) {
    fun postQuery(
        prompt: String
    ): Call<ResponseBody> {
        return aiService.postQuery(prompt)
    }
}