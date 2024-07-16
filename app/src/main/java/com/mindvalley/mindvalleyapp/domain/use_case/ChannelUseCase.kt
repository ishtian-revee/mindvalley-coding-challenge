package com.mindvalley.mindvalleyapp.domain.use_case

import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Channel
import kotlinx.coroutines.flow.Flow

interface ChannelUseCase {
    suspend fun getChannels(): Flow<Resource<List<Channel>>>
}