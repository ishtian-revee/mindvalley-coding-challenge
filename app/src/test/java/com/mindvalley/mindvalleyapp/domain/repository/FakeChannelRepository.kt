package com.mindvalley.mindvalleyapp.domain.repository

import com.mindvalley.mindvalleyapp.common.Resource
import com.mindvalley.mindvalleyapp.domain.model.Category
import com.mindvalley.mindvalleyapp.domain.model.Channel
import com.mindvalley.mindvalleyapp.domain.model.ChannelName
import com.mindvalley.mindvalleyapp.domain.model.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeChannelRepository : ChannelRepository {

    private val episodes = mutableListOf<Media>()
    private val channels = mutableListOf<Channel>()
    private val categories = mutableListOf<Category>()

    override suspend fun getNewEpisodes(): Flow<Resource<List<Media>>> {
        ('a'..'z').forEachIndexed { _, c ->
            episodes.add(
                Media(
                    channel = ChannelName(title = c.toString()),
                    coverAsset = null,
                    title = c.toString(),
                    type = c.toString()
                )
            )
        }
        return flow { emit(Resource.Success(episodes)) }
    }

    override suspend fun getChannels(): Flow<Resource<List<Channel>>> {
        ('a'..'z').forEachIndexed { _, c ->
            channels.add(
                Channel(
                    coverAsset = null,
                    iconAsset = null,
                    latestMedia = listOf(),
                    mediaCount = 100,
                    seriesList = listOf(),
                    slug = c.toString(),
                    title = c.toString()
                )
            )
        }
        return flow { emit(Resource.Success(channels)) }
    }

    override suspend fun getCategories(): Flow<Resource<List<Category>>> {
        ('a'..'z').forEachIndexed { _, c ->
            categories.add(
                Category(
                    name = c.toString()
                )
            )
        }
        return flow { emit(Resource.Success(categories)) }
    }
}