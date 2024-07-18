package com.mindvalley.mindvalleyapp.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mindvalley.mindvalleyapp.data.model.CategoryEntity
import com.mindvalley.mindvalleyapp.data.model.ChannelEntity
import com.mindvalley.mindvalleyapp.data.model.MediaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewEpisodes(episodes: List<MediaEntity>): Array<Long>

    @Query("SELECT * FROM mediaEntity")
    fun getNewEpisodes(): Flow<List<MediaEntity>>

    @Query("DELETE FROM mediaEntity")
    fun deleteAllEpisodes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannels(channels: List<ChannelEntity>): Array<Long>

    @Query("SELECT * FROM channelEntity")
    fun getChannels(): Flow<List<ChannelEntity>>

    @Query("DELETE FROM channelEntity")
    fun deleteAllChannels()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>): Array<Long>

    @Query("SELECT * FROM categoryEntity")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("DELETE FROM categoryEntity")
    fun deleteAllCategories()
}