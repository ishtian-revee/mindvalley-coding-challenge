package com.mindvalley.mindvalleyapp.domain.use_case

import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Media
import com.mindvalley.mindvalleyapp.domain.repository.ChannelRepository
import kotlinx.coroutines.flow.Flow

class GetNewEpisodesUseCase(
    private val channelRepository: ChannelRepository
) : NewEpisodeUseCase {
    override suspend fun getNewEpisodes(): Flow<Resource<List<Media>>> =
        channelRepository.getNewEpisodes()
}