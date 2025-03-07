package com.emirhankolver.sampleaichat.domain.ai

import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class AIRepository @Inject constructor(
    private val dataSource: AIDataSource
) {
    fun postQuery(
        prompt: String,
    ): Call<ResponseBody> {
        return dataSource.postQuery(prompt)
    }
}