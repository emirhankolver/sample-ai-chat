package com.emirhankolver.sampleaichat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao {
    @Insert
    suspend fun insert(chat: MessageEntity)

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp DESC")
    fun getAll(chatId: String): Flow<List<MessageEntity>>

    @Query("DELETE FROM messages WHERE id = :chatId")
    suspend fun deleteMessages(chatId: String)

    @Query("DELETE FROM messages")
    suspend fun deleteAll()

    @Query("UPDATE messages SET message = :newText WHERE id = :messageId")
    fun updateMessage(messageId: String, newText: String)


}