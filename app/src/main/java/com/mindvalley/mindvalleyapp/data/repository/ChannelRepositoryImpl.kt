package com.mindvalley.mindvalleyapp.data.repository

import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.data.local.room.AppDatabase
import com.mindvalley.mindvalleyapp.data.remote.MindvalleyApi
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.domain.model.Media
import com.mindvalley.mindvalleyapp.domain.repository.ChannelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChannelRepositoryImpl @Inject constructor(
    val api: MindvalleyApi,
    val database: AppDatabase
): ChannelRepository {
    override suspend fun getNewEpisodes(): Flow<Resource<List<Media>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getChannels(): Flow<Resource<List<Channel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): Flow<Resource<List<Category>>> {
        TODO("Not yet implemented")
    }
}