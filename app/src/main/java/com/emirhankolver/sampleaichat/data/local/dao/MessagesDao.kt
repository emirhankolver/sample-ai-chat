package com.emirhankolver.sampleaichat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirhankolver.sampleaichat.data.local.entities.MessageEntity

@Dao
interface MessagesDao {
    @Insert
    suspend fun insert(chat: MessageEntity)

    @Query("SELECT * FROM messages WHERE chatId = :chatId")
    suspend fun getAll(chatId: String): List<MessageEntity>
}