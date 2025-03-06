package com.emirhankolver.sampleaichat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirhankolver.sampleaichat.data.local.entities.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatsDao {
    @Insert
    suspend fun insert(chat: ChatEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM chats WHERE id = :chatId)")
    suspend fun exists(chatId: String): Boolean

    @Query("SELECT * FROM chats WHERE name LIKE '%' || :query || '%' ORDER BY updatedAt DESC")
    fun search(query: String): Flow<List<ChatEntity>>

    @Query("DELETE FROM chats WHERE id = :chatId")
    suspend fun delete(chatId: String)

    @Query("DELETE FROM chats")
    suspend fun deleteAll()

}