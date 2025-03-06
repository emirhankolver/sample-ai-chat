package com.emirhankolver.sampleaichat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity

@Dao
interface MessagesDao {
    @Insert
    suspend fun insert(chat: MessageEntity)

    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp DESC")
    suspend fun getAll(chatId: String): List<MessageEntity>

    @Query("DELETE FROM messages WHERE id = :chatId")
    suspend fun deleteMessages(chatId: String)


    @Query("DELETE FROM messages")
    suspend fun deleteAll()
}