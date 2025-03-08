package com.emirhankolver.sampleaichat.domain.ai

import com.emirhankolver.sampleaichat.model.QueryRequest
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class AIDataSource @Inject constructor(
    private val aiService: AIService,
) {
    fun postQuery(
        requestBody: QueryRequest
    ): Call<ResponseBody> {
        return aiService.postQuery(requestBody)
    }
}