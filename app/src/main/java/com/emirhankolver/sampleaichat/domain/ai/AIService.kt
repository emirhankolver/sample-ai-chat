package com.emirhankolver.sampleaichat.domain.ai

import com.emirhankolver.sampleaichat.model.QueryRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Streaming

interface AIService {

    @POST("api/v1/ai/query")
    @Streaming
    fun postQuery(
        @Body requestBody: QueryRequest
    ): Call<ResponseBody>
}