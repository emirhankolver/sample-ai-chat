package com.emirhankolver.sampleaichat.domain

import com.emirhankolver.sampleaichat.data.local.dao.MessagesDao
import com.emirhankolver.sampleaichat.domain.ai.AIRepository
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
    private val TAG = "AIUseCase"


    fun postQuery(
        prompt: String,
        messageId: String
    ) = flow<String> {
        val response = repository.postQuery(prompt).execute()

        if (response.isSuccessful) {
            val input = response.body()?.byteStream()?.bufferedReader() ?: throw Exception()

            try {
                val stringBuilder = StringBuilder()
                var responseText = ""

                while (currentCoroutineContext().isActive) {
                    val char = input.read().toChar() // Read byte and convert to char

                    if (char == Char.MIN_VALUE) break // End of stream

                    if (char.isWhitespace()) {
                        // If newline (`\n`), emit it separately
                        if (char == '\n') {
                            if (stringBuilder.isNotEmpty()) {
                                val word = stringBuilder.toString()
                                responseText += "$word "
                                messagesDao.updateMessage(messageId, responseText)
                                emit(word)
                                stringBuilder.clear()
                            }
                            responseText += "\n"
                            messagesDao.updateMessage(messageId, responseText)
                            emit("\n")
                        } else if (stringBuilder.isNotEmpty()) {
                            // Emit word when encountering space or tab
                            val word = stringBuilder.toString()
                            responseText += "$word "
                            messagesDao.updateMessage(messageId, responseText)
                            emit(word)
                            stringBuilder.clear()
                        }
                    } else {
                        stringBuilder.append(char)
                    }
                }
            } catch (e: Throwable) {
                throw Exception(e)
            } finally {
                input.close()
            }
        } else {
            throw HttpException(response)
        }
    }


        .flowOn(Dispatchers.IO)
}