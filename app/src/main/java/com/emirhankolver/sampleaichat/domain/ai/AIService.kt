package com.emirhankolver.sampleaichat.domain.ai

import okhttp3.ResponseBody
import retrofit2.Call
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
    ): Call<ResponseBody>
}