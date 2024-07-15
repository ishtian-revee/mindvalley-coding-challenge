package com.mindvalley.mindvalleyapp.data.local

import com.mindvalley.mindvalleyapp.data.local.room.ChannelDao
import com.mindvalley.mindvalleyapp.data.model.CategoryEntity
import com.mindvalley.mindvalleyapp.data.model.ChannelEntity
import com.mindvalley.mindvalleyapp.data.model.MediaEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val channelDao: ChannelDao
) {
    fun getNewEpisodes(): Flow<List<MediaEntity>> = channelDao.getNewEpisodes()
    suspend fun insertNewEpisodes(episodes: List<MediaEntity>): Array<Long> {
        return channelDao.insertNewEpisodes(episodes)
    }

    fun getChannels(): Flow<List<ChannelEntity>> = channelDao.getChannels()
    suspend fun insertChannels(channels: List<ChannelEntity>): Array<Long> {
        return channelDao.insertChannels(channels)
    }

    fun getCategories(): Flow<List<CategoryEntity>> = channelDao.getCategories()
    suspend fun insertCategories(categories: List<CategoryEntity>): Array<Long> {
        return channelDao.insertCategories(categories)
    }
}