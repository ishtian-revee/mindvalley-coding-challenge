package com.mindvalley.mindvalleyapp.domain.use_case

import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface NewEpisodeUseCase {
    suspend fun getNewEpisodes(): Flow<Resource<List<Media>>>
}