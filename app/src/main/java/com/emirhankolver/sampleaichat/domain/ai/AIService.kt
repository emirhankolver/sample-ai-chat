package com.emirhankolver.sampleaichat.domain.ai

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Streaming

interface AIService {

    @FormUrlEncoded
    @POST("api/v1/ai/query")
    @Streaming
    fun postQuery(
        @Field("prompt") prompt: String
    ): Flow<String>
}