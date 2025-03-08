package com.emirhankolver.sampleaichat.domain

import com.emirhankolver.sampleaichat.data.local.dao.MessagesDao
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity
import com.emirhankolver.sampleaichat.domain.ai.AIRepository
import com.emirhankolver.sampleaichat.model.QueryRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import retrofit2.HttpException
import javax.inject.Inject

class AIUseCase @Inject constructor(
    private val repository: AIRepository,
    private val messagesDao: MessagesDao,
) {

    fun postQuery(
        prompt: String,
        messageId: String,
        messageHistory: List<MessageEntity> = emptyList(),
    ) = flow {
        val response = repository.postQuery(
            QueryRequest(
                prompt, messageHistory.reversed()
            )
        ).execute()

        if (response.isSuccessful) {
            val input = response.body()?.byteStream()?.bufferedReader() ?: throw Exception()

            try {
                var responseText = ""
                while (currentCoroutineContext().isActive) {
                    val charCode = input.read()
                    if (charCode == -1) break
                    val char = charCode.toChar()
                    responseText += char
                    messagesDao.updateMessage(messageId, responseText)
                    emit(responseText)
                }
            } catch (e: Throwable) {
                throw Exception(e)
            } finally {
                input.close()
            }
        } else {
            throw HttpException(response)
        }
    }.flowOn(Dispatchers.IO)
}