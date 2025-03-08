package com.emirhankolver.sampleaichat.domain.ai

import com.emirhankolver.sampleaichat.model.QueryRequest
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class AIRepository @Inject constructor(
    private val dataSource: AIDataSource
) {
    fun postQuery(
        requestBody: QueryRequest
    ): Call<ResponseBody> {
        return dataSource.postQuery(requestBody)
    }
}