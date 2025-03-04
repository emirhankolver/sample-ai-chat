package com.emirhankolver.sampleaichat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirhankolver.sampleaichat.data.local.entities.ChatEntity

@Dao
interface ChatsDao {
    @Insert
    suspend fun insert(chat: ChatEntity)

    @Query("SELECT * FROM chats")
    suspend fun getAll(): List<ChatEntity>
}