package com.mindvalley.mindvalleyapp.domain.repository

import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface ChannelRepository {
    suspend fun getNewEpisodes(): Flow<Resource<List<Media>>>
    suspend fun getChannels(): Flow<Resource<List<Channel>>>
    suspend fun getCategories(): Flow<Resource<List<Category>>>
}